package org.lab1

import java.sql.SQLException
import java.sql.Statement
import java.util.logging.Level
import java.util.logging.Logger

class PostgreSQLDAO {
    val all: List<ComicLib>
        get() {
            val comics: MutableList<ComicLib> = ArrayList<ComicLib>()
            try {
                ConnectionUtil.connection.use { connection ->
                    val stmt: Statement? = connection?.createStatement()
                    val rs = stmt?.executeQuery("select * from comics") ?: return emptyList()
                    while (rs.next()) {
                        val id = rs.getInt("id")
                        val title = rs.getString("title")
                        val author = rs.getString("author")
                        val genre = rs.getString("genre")
                        val isbn = rs.getString("isbn")
                        val year = rs.getInt("year")

                        val comic: ComicLib = ComicLib(id, title, author, genre, isbn, year)
                        comics.add(comic)
                    }
                }
            } catch (ex: SQLException) {
                Logger.getLogger(PostgreSQLDAO::class.java.name).log(
                    Level.SEVERE,
                    null, ex
                )
            }
            return comics
        }
}