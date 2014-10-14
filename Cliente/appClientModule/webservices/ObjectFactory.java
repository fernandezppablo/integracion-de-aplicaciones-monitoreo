
package webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RegistrarVenta_QNAME = new QName("http://webservices/", "registrarVenta");
    private final static QName _InformarLogResponse_QNAME = new QName("http://webservices/", "informarLogResponse");
    private final static QName _InformarLog_QNAME = new QName("http://webservices/", "informarLog");
    private final static QName _RegistrarVentaResponse_QNAME = new QName("http://webservices/", "registrarVentaResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarVentaResponse }
     * 
     */
    public RegistrarVentaResponse createRegistrarVentaResponse() {
        return new RegistrarVentaResponse();
    }

    /**
     * Create an instance of {@link InformarLog }
     * 
     */
 
    /**
     * Create an instance of {@link RegistrarVenta }
     * 
     */
    public RegistrarVenta createRegistrarVenta() {
        return new RegistrarVenta();
    }

    /**
     * Create an instance of {@link InformarLogResponse }
     * 
     */
    public InformarLogResponse createInformarLogResponse() {
        return new InformarLogResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarVenta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "registrarVenta")
    public JAXBElement<RegistrarVenta> createRegistrarVenta(RegistrarVenta value) {
        return new JAXBElement<RegistrarVenta>(_RegistrarVenta_QNAME, RegistrarVenta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformarLogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "informarLogResponse")
    public JAXBElement<InformarLogResponse> createInformarLogResponse(InformarLogResponse value) {
        return new JAXBElement<InformarLogResponse>(_InformarLogResponse_QNAME, InformarLogResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InformarLog }{@code >}}
     * 
     */

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarVentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "registrarVentaResponse")
    public JAXBElement<RegistrarVentaResponse> createRegistrarVentaResponse(RegistrarVentaResponse value) {
        return new JAXBElement<RegistrarVentaResponse>(_RegistrarVentaResponse_QNAME, RegistrarVentaResponse.class, null, value);
    }

}
