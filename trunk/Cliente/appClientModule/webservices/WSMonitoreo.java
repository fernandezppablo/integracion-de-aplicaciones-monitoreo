package webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2014-10-08T16:41:29.919-03:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebService(targetNamespace = "http://webservices/", name = "WSMonitoreo")
@XmlSeeAlso({ObjectFactory.class})
public interface WSMonitoreo {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "informarLog", targetNamespace = "http://webservices/", className = "webservices.InformarLog")
    @WebMethod
    @ResponseWrapper(localName = "informarLogResponse", targetNamespace = "http://webservices/", className = "webservices.InformarLogResponse")
    public java.lang.String informarLog(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
