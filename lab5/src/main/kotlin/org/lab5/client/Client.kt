package org.lab5.client

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

import kotlinx.coroutines.runBlocking
import org.lab5.ComicLib
import java.util.*


class ComicServiceClientCLI(
    private val client: HttpClient
) {
    fun run() {
        val scanner = Scanner(System.`in`)

        while (true) {
            println("Выберите действие: c - Добавить комикс, r - Получить комикс, u - Обновить комикс, d - Удалить комикс, e - Выйти")
            val choice = scanner.nextLine()

            when (choice) {
                "c" -> {
                    // Логика добавления комикса
                    val comic = inputComic()
                    if (comic == null) {
                        println("Проверьте корректность введенных данных")
                        continue
                    }
                    runBlocking {
                        createComic(comic)
                        println("Комикс создан")
                    }
                }
                "r" -> {
                    // Логика получения комикса
                    println("Введите ID комикса:")
                    val id = scanner.nextLine().toInt()
                    runBlocking {
                        val comic = getComic(id)
                        println("Комикс с id $id: $comic")
                    }
                }
                "u" -> {
                    // Логика обновления комикса
                    println("Введите ID комикса для обновления:")
                    val id = scanner.nextLine().toInt()

                    val updatedComic = inputComic()
                    if (updatedComic != null) {
                        updatedComic.id = id
                    } else continue;

                    runBlocking {
                        val success = updateComic(id, updatedComic)
                        if (success) println("Комикс обновлен успешно") else println("Ошибка при обновлении комикса")
                    }
                }
                "d" -> {
                    // Логика удаления комикса
                    println("Введите ID комикса для удаления:")
                    val id = scanner.nextLine().toInt()
                    runBlocking {
                        val success = deleteComic(id)
                        if (success) println("Комикс удален успешно") else println("Ошибка при удалении комикса")
                    }
                }
                "e" -> {
                    println("Выход из программы...")
                    return
                }
                else -> {
                    println("Некорректный ввод. Попробуйте снова.")
                }
            }

        }
    }

    private fun inputComic(): ComicLib? {
        val scanner = Scanner(System.`in`)

        // Запрашиваем данные для обновления
        println("Введите название комикса:")
        val title = scanner.nextLine()
        println("Введите автора комикса:")
        val author = scanner.nextLine()
        println("Введите жанр комикса:")
        val genre = scanner.nextLine()
        println("Введите isbn комикса:")
        val isbn = scanner.nextLine()
        println("Введите год издания комикса:")
        val yearInput = scanner.nextLine()
        val year: Int;

        try {
            year = yearInput?.toInt() ?: throw NumberFormatException("Год не введен")
        } catch (e: NumberFormatException) {
            println("Ошибка: неверный формат года. Пожалуйста, введите число.")
            return null
        }

        val comic = ComicLib(0, title, author, genre, isbn, year ) // Создаем объект комикса
        return comic
    }

    suspend fun getAllComics(): List<ComicLib> {
        val comics: List<ComicLib> = client.get("http://localhost:8080/comics").body()
        return comics
    }

    private suspend fun getComic(id: Int): ComicLib {
        val comic: ComicLib = client.get("http://localhost:8080/comics/$id").body()
        return comic
    }

    private suspend fun createComic(comic: ComicLib): Boolean {
        val response = client.post("http://localhost:8080/comics") {
            contentType(ContentType.Application.Json)
            setBody(comic)
        }
        return response.status.value == 200
    }

    private suspend fun updateComic(id: Int, comic: ComicLib): Boolean {
        val response = client.put("http://localhost:8080/comics/$id") {
            contentType(ContentType.Application.Json)
            setBody(comic)
        }
        return response.status.value == 200
    }

    private suspend fun deleteComic(id: Int): Boolean {
        val response = client.delete("http://localhost:8080/comics/$id")
        return response.status.value == 200
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json()
                }
            }

            val clientCLI = ComicServiceClientCLI(client)
            clientCLI.run()
        }
    }

}

