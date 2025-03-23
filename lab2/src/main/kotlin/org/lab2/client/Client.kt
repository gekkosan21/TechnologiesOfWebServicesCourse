package org.lab2.client

import org.eclipse.persistence.internal.sessions.DirectCollectionChangeRecord.NULL
import org.lab2.ComicLib
import org.lab2.ComicService
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class ComicServiceClientCLI(
    private val comicService: ComicService
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
                    val success = comicService.createComic(comic)
                    if (success) println("Комикс добавлен успешно") else println("Ошибка при добавлении комикса")
                }
                "r" -> {
                    // Логика получения комикса
                    println("Введите ID комикса:")
                    val id = scanner.nextLine().toInt()
                    val comic = comicService.getComic(id)
                    println("Комикс: $comic")
                }
                "u" -> {
                    // Логика обновления комикса
                    println("Введите ID комикса для обновления:")
                    val id = scanner.nextLine().toInt()

                    val updatedComic = inputComic()
                    if (updatedComic != null) {
                        updatedComic.id = id
                    };

                    val success = comicService.updateComic(updatedComic)
                    if (success) println("Комикс обновлен успешно") else println("Ошибка при обновлении комикса")
                }
                "d" -> {
                    // Логика удаления комикса
                    println("Введите ID комикса для удаления:")
                    val id = scanner.nextLine().toInt()
                    val success = comicService.deleteComic(id)
                    if (success) println("Комикс удален успешно") else println("Ошибка при удалении комикса")
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

    fun inputComic(): ComicLib? {
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

    companion object {
        // Методы для взаимодействия с comicService (добавление, получение, обновление, удаление комиксов)
        @JvmStatic
        fun main(args: Array<String>) {
            val comicService: ComicService = ComicService()

            val clientCLI = ComicServiceClientCLI(comicService)
            clientCLI.run()
        }
    }
}
