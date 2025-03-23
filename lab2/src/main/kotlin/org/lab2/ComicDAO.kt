package org.lab2

import java.sql.*


//class ComicDAO(private val jdbcURL: String, private val jdbcUsername: String, private val jdbcPassword: String) {
class ComicDAO {
    private var jdbcConnection: Connection? = null

    @Throws(SQLException::class)
    protected fun connect() {
        if (jdbcConnection == null || jdbcConnection!!.isClosed) {
            jdbcConnection = ConnectionUtil.connection;
//            try {
//                Class.forName("org.postgresql.Driver")
//            } catch (e: ClassNotFoundException) {
//                throw SQLException(e)
//            }
//            jdbcConnection = DriverManager.getConnection(
//                jdbcURL, jdbcUsername, jdbcPassword
//            )
        }
    }

    @Throws(SQLException::class)
    protected fun disconnect() {
        if (jdbcConnection != null && !jdbcConnection!!.isClosed) {
            jdbcConnection!!.close()
        }
    }

    @Throws(SQLException::class)
    fun insertComic(comic: ComicLib): Boolean {
        val sql = "INSERT INTO comics (title, author, genre, year, isbn) VALUES (?, ?, ?, ?, ?)"
        connect()

        val statement = jdbcConnection!!.prepareStatement(sql)
        statement.setString(1, comic.title)
        statement.setString(2, comic.author)
        statement.setString(3, comic.genre)
        statement.setInt(4, comic.year)
        statement.setString(5, comic.isbn)

        val rowInserted = statement.executeUpdate() > 0
        statement.close()
        disconnect()
        return rowInserted
    }

    @Throws(SQLException::class)
    fun listAllComics(): List<ComicLib> {
        val listComic: MutableList<ComicLib> = ArrayList()

        val sql = "SELECT * FROM comics"

        connect()

        val statement = jdbcConnection!!.createStatement()
        val resultSet = statement.executeQuery(sql)

        while (resultSet.next()) {
            val id = resultSet.getInt("id")
            val title = resultSet.getString("title")
            val author = resultSet.getString("author")
            val genre = resultSet.getString("genre")
            val year = resultSet.getInt("year")
            val isbn = resultSet.getString("isbn")

            val comic = ComicLib(id, title, author, genre, isbn, year)
            listComic.add(comic)
        }

        resultSet.close()
        statement.close()

        disconnect()

        return listComic
    }

    @Throws(SQLException::class)
    fun getComic(id: Int): ComicLib? {
        var comic: ComicLib? = null
        val sql = "SELECT * FROM comics WHERE id = ?"

        connect()

        val statement = jdbcConnection!!.prepareStatement(sql)
        statement.setInt(1, id)

        val resultSet = statement.executeQuery()

        if (resultSet.next()) {
            val title = resultSet.getString("title")
            val author = resultSet.getString("author")
            val genre = resultSet.getString("genre")
            val year = resultSet.getInt("year")
            val isbn = resultSet.getString("isbn")

            comic = ComicLib(id, title, author, genre, isbn, year)
        }

        resultSet.close()
        statement.close()

        disconnect()

        return comic
    }


    @Throws(SQLException::class)
    fun updateComic(comic: ComicLib): Boolean {
        val sql = "UPDATE comics SET title = ?, author = ?, genre = ?, year = ?, isbn = ? WHERE id = ?"
        connect()

        val statement = jdbcConnection!!.prepareStatement(sql)
        statement.setString(1, comic.title)
        statement.setString(2, comic.author)
        statement.setString(3, comic.genre)
        statement.setInt(4, comic.year)
        statement.setString(5, comic.isbn)
        statement.setInt(6, comic.id)

        val rowUpdated = statement.executeUpdate() > 0
        statement.close()
        disconnect()
        return rowUpdated
    }

    @Throws(SQLException::class)
    fun deleteComic(id: Int): Boolean {
        val sql = "DELETE FROM comics WHERE id = ?"
        connect()

        val statement = jdbcConnection!!.prepareStatement(sql)
        statement.setInt(1, id)

        val rowDeleted = statement.executeUpdate() > 0
        statement.close()
        disconnect()
        return rowDeleted
    }
}

