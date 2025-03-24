//package org.lab4
//
//import java.sql.SQLException
//
//
//@WebService(serviceName = "ComicService")
//class ComicService {
//    var soapFaultBuilder: SOAPFaultBuilder? = null
//
//    @WebMethod(operationName = "getAll")
//    open fun getAll(): List<ComicLib> {
//        val dao = ComicDAO()
//        return dao.listAllComics()
//    }
//
//    @WebMethod(operationName = "createComic")
//    open fun createComic(comic: ComicLib?): Boolean {
//        val comicDAO = ComicDAO()
//        try {
//            return comicDAO.insertComic(comic)
//        } catch (e: SQLException) {
//            e.printStackTrace()
//            return false
//        }
//    }
//
//    @WebMethod(operationName = "getComic")
//    open fun getComic(id: Int): ComicLib? {
//        val comicDAO = ComicDAO()
//        try {
//            return comicDAO.getComic(id)
//        } catch (e: SQLException) {
//            e.printStackTrace()
//            return null
//        }
//    }
//
//    @WebMethod(operationName = "updateComic")
//    open fun updateComic(comic: ComicLib?): Boolean {
//        val comicDAO = ComicDAO()
//        try {
//            return comicDAO.updateComic(comic!!)
//        } catch (e: SQLException) {
//            e.printStackTrace()
//            return false
//        }
//    }
//
//    @WebMethod(operationName = "deleteComic")
//    open fun deleteComic(id: Int): Boolean {
//        val comicDAO = ComicDAO()
//        try {
//            return comicDAO.deleteComic(id)
//        } catch (e: SQLException) {
//            e.printStackTrace()
//            return false
//        }
//    }
//}