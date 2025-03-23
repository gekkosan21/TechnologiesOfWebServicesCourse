package org.lab1

import com.sun.xml.ws.fault.SOAPFaultBuilder
import javax.jws.WebMethod
import javax.jws.WebService

@WebService(serviceName = "comicService")
class ComicService {
    var soapFaultBuilder: SOAPFaultBuilder? = null

    @WebMethod(operationName = "getAll")
    open fun getAll(): List<ComicLib> {
        val dao = PostgreSQLDAO()
        return dao.all
    }
}