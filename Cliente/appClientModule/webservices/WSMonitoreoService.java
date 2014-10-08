package webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.7.redhat-1
 * 2014-10-08T16:41:29.958-03:00
 * Generated source version: 2.7.7.redhat-1
 * 
 */
@WebServiceClient(name = "WSMonitoreoService", 
                  wsdlLocation = "http://MacBook-Air-de-Octavio.local:8080/Monitoreo/WSMonitoreo?wsdl",
                  targetNamespace = "http://webservices/") 
public class WSMonitoreoService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservices/", "WSMonitoreoService");
    public final static QName WSMonitoreoPort = new QName("http://webservices/", "WSMonitoreoPort");
    static {
        URL url = null;
        try {
            url = new URL("http://MacBook-Air-de-Octavio.local:8080/Monitoreo/WSMonitoreo?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(WSMonitoreoService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://MacBook-Air-de-Octavio.local:8080/Monitoreo/WSMonitoreo?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public WSMonitoreoService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSMonitoreoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSMonitoreoService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSMonitoreoService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSMonitoreoService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public WSMonitoreoService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName);
    }

    /**
     *
     * @return
     *     returns WSMonitoreo
     */
    @WebEndpoint(name = "WSMonitoreoPort")
    public WSMonitoreo getWSMonitoreoPort() {
        return super.getPort(WSMonitoreoPort, WSMonitoreo.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSMonitoreo
     */
    @WebEndpoint(name = "WSMonitoreoPort")
    public WSMonitoreo getWSMonitoreoPort(WebServiceFeature... features) {
        return super.getPort(WSMonitoreoPort, WSMonitoreo.class, features);
    }

}
