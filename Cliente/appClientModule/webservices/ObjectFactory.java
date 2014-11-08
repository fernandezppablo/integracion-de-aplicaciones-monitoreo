
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
    private final static QName _GenerarRespuestaResponse_QNAME = new QName("http://webservices/", "generarRespuestaResponse");
    private final static QName _RegistrarVentaResponse_QNAME = new QName("http://webservices/", "registrarVentaResponse");
    private final static QName _EnviarInformeAuditoriaResponse_QNAME = new QName("http://webservices/", "enviarInformeAuditoriaResponse");
    private final static QName _EnviarInformeAuditoria_QNAME = new QName("http://webservices/", "enviarInformeAuditoria");
    private final static QName _GenerarRespuesta_QNAME = new QName("http://webservices/", "generarRespuesta");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GenerarRespuesta }
     * 
     */
    public GenerarRespuesta createGenerarRespuesta() {
        return new GenerarRespuesta();
    }

    /**
     * Create an instance of {@link EnviarInformeAuditoria }
     * 
     */
    public EnviarInformeAuditoria createEnviarInformeAuditoria() {
        return new EnviarInformeAuditoria();
    }

    /**
     * Create an instance of {@link EnviarInformeAuditoriaResponse }
     * 
     */
    public EnviarInformeAuditoriaResponse createEnviarInformeAuditoriaResponse() {
        return new EnviarInformeAuditoriaResponse();
    }

    /**
     * Create an instance of {@link RegistrarVentaResponse }
     * 
     */
    public RegistrarVentaResponse createRegistrarVentaResponse() {
        return new RegistrarVentaResponse();
    }

    /**
     * Create an instance of {@link RegistrarVenta }
     * 
     */
    public RegistrarVenta createRegistrarVenta() {
        return new RegistrarVenta();
    }

    /**
     * Create an instance of {@link GenerarRespuestaResponse }
     * 
     */
    public GenerarRespuestaResponse createGenerarRespuestaResponse() {
        return new GenerarRespuestaResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarRespuestaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "generarRespuestaResponse")
    public JAXBElement<GenerarRespuestaResponse> createGenerarRespuestaResponse(GenerarRespuestaResponse value) {
        return new JAXBElement<GenerarRespuestaResponse>(_GenerarRespuestaResponse_QNAME, GenerarRespuestaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarVentaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "registrarVentaResponse")
    public JAXBElement<RegistrarVentaResponse> createRegistrarVentaResponse(RegistrarVentaResponse value) {
        return new JAXBElement<RegistrarVentaResponse>(_RegistrarVentaResponse_QNAME, RegistrarVentaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarInformeAuditoriaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "enviarInformeAuditoriaResponse")
    public JAXBElement<EnviarInformeAuditoriaResponse> createEnviarInformeAuditoriaResponse(EnviarInformeAuditoriaResponse value) {
        return new JAXBElement<EnviarInformeAuditoriaResponse>(_EnviarInformeAuditoriaResponse_QNAME, EnviarInformeAuditoriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarInformeAuditoria }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "enviarInformeAuditoria")
    public JAXBElement<EnviarInformeAuditoria> createEnviarInformeAuditoria(EnviarInformeAuditoria value) {
        return new JAXBElement<EnviarInformeAuditoria>(_EnviarInformeAuditoria_QNAME, EnviarInformeAuditoria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerarRespuesta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "generarRespuesta")
    public JAXBElement<GenerarRespuesta> createGenerarRespuesta(GenerarRespuesta value) {
        return new JAXBElement<GenerarRespuesta>(_GenerarRespuesta_QNAME, GenerarRespuesta.class, null, value);
    }

}
