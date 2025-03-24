package org.lab5

import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.request.*
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
                get("{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                    id?.let {
                        val comic = comicDao.getComic(it)
                        if (comic != null) call.respond(comic) else call.respond(HttpStatusCode.NotFound)
                    } ?: call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                }
                post {
                    val comic = call.receive<ComicLib>()
                    val created = comicDao.insertComic(comic)
                    if (created) call.respond(HttpStatusCode.Created) else call.respond(HttpStatusCode.InternalServerError)
                }
                put("{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                    id?.let {
                        val comic = call.receive<ComicLib>()
                        val updated = comicDao.updateComic(comic)
                        if (updated) call.respond(HttpStatusCode.OK) else call.respond(HttpStatusCode.NotFound)
                    } ?: call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                }
                delete("{id}") {
                    val id = call.parameters["id"]?.toIntOrNull()
                    id?.let {
                        val deleted = comicDao.deleteComic(it)
                        if (deleted) call.respond(HttpStatusCode.OK) else call.respond(HttpStatusCode.NotFound)
                    } ?: call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                }
            }
        }
    }.start(wait = true)
}
