
package uade.fain.ia.tpo.interfaces.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArticuloDesconocidoException complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArticuloDesconocidoException">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="articuloId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArticuloDesconocidoException", propOrder = {
    "articuloId",
    "message"
})
public class ArticuloDesconocidoException {

    @XmlElement(required = true, nillable = true)
    protected String articuloId;
    protected String message;

    /**
     * Obtiene el valor de la propiedad articuloId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArticuloId() {
        return articuloId;
    }

    /**
     * Define el valor de la propiedad articuloId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArticuloId(String value) {
        this.articuloId = value;
    }

    /**
     * Obtiene el valor de la propiedad message.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Define el valor de la propiedad message.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
