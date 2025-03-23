package org.lab3

import javax.xml.ws.Endpoint

object App {
    @JvmStatic
    fun main(args: Array<String>) {
        val url = "http://localhost:8080/ComicService"
        Endpoint.publish(url, ComicService())
    }
}
