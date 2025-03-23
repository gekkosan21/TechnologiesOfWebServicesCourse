package org.lab1.client

import org.lab1.ComicLib
import org.lab1.ComicService
import java.net.MalformedURLException
import java.net.URL


object Client {
    @Throws(MalformedURLException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val url = URL("http://localhost:8083/lab1_j2ee_1_0_SNAPSHOT_war/ws/comic?wsdl")
        val comicService: ComicService = ComicService()

        val comics: List<ComicLib> = comicService.getAll()
        println("All comics")
        for (comic in comics) {
            println(
                ("Comics {" + "id=" + comic.id
                        ).toString() + ", title=" + comic.title.toString() +
                        ", author=" + comic.author +
                        ", genre=" + comic.genre +
                        ", isbn=" + comic.isbn +
                        ", year=" + comic.year + '}'
            )
        }
        println("Total comics: " + comics.size)

        println("\nComics older than 1990")
        for (comic in comics) {
            if (comic.year < 1990) {
                System.out.println(
                    ("comic {Id=" + comic.id).toString() + ", title=" + comic.title.toString() + ", author=" +
                            comic.author + ", genre=" + comic.genre + ", isbn=" + comic.isbn +
                            ", year=" + comic.year + "}"
                )
            }
        }

        println("\nHistorical Comics")
        for (comic in comics) {
            if (comic.genre == "Historical") {
                System.out.println(
                    ("comic {Id=" + comic.id + ", title=" + comic.title + ", author=" +
                            comic.author + ", genre=" + comic.genre + ", isbn=" + comic.isbn +
                            ", year=" + comic.year + "}"
                ))
            }
        }
    }
}
