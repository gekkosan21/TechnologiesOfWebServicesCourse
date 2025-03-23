package org.lab4

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
//        install(CallLogging) {
//            level = Level.INFO
//        }

        val comicDao = ComicDAO() // Создание экземпляра DAO

        routing {
            route("/comics") {
                get {
                    call.respond(comicDao.listAllComics())
                }
            }
        }
    }.start(wait = true)
}
