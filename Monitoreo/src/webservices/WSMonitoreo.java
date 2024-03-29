package webservices;

import interfaces.AuditoriaDAOInterfaz;
import interfaces.TROrdenVentaDAOInterfaz;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import negocio.Auditoria;
import negocio.ItemAuditoria;
import dto.AuditoriaDTO;
import dto.ItemAuditoriaDTO;
import dto.MensajeRespuestaDTO;
import dto.TROrdenVentaDTO;


@WebService
public class WSMonitoreo{

	@EJB(name = "AuditoriaDAO")
	private AuditoriaDAOInterfaz daoauditoria;
	@EJB(name = "TROrdenVentaDAO")
	private TROrdenVentaDAOInterfaz daoventa;
	@WebMethod
	public String enviarInformeAuditoria(String xml) {

			JAXBContext jaxbcontext;
			try {
				jaxbcontext = JAXBContext.newInstance(ItemAuditoriaDTO.class);
				System.out.println(xml);
				javax.xml.bind.Unmarshaller desencripta = jaxbcontext.createUnmarshaller();
				ItemAuditoriaDTO item = (ItemAuditoriaDTO) desencripta.unmarshal(new StringReader(xml));
				item.setFecha(item.getFecha());
				if(item.getFecha()==null){
					return generarRespuesta(false,"Error en el formato de la fecha");
				}
				
				AuditoriaDTO nueva = new AuditoriaDTO();
				nueva.agregarItemAuditoria(item);
				daoauditoria.grabarAuditoria(nueva);


			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				return generarRespuesta(false,e.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		return generarRespuesta(true,"Carga finalizada correctamente");
	}

	/************************************************/
	/* SERVICIO QUE RECIBE UNA VENTA Y LA GUARDA,   */
	/* NO ASOCIA A LA VENTA NINGUNA ORDEN DE        */
	/* TABLAS (INSERT): TRORDENVENTA,ITEMORDENVENTA */
	/* TABLAS (UPDATE): ARTICULO (CDAD VENTAS)      */
	/************************************************/
	@WebMethod
	public String registrarVenta(String xml)
	{
		
		JAXBContext jaxbcontext;
		System.out.println(xml);
		try {
			//Primero deserializamos la orden de venta
			jaxbcontext = JAXBContext.newInstance(TROrdenVentaDTO.class);
			javax.xml.bind.Unmarshaller desencripta = jaxbcontext.createUnmarshaller();
			TROrdenVentaDTO trorden = (TROrdenVentaDTO) desencripta.unmarshal(new StringReader(xml));
			if(trorden.getFecha()!=null){
				try {
					daoventa.grabarVenta(trorden);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return generarRespuesta(false,e.getMessage());
				}
			}else{
				return generarRespuesta(false,"Error en el formato de la fecha");
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return generarRespuesta(false,"Error en el formato del xml ingresado");

		}
	
	   
		return generarRespuesta(true,"Carga finalizada correctamente");
		
	}

	private String generarRespuesta(boolean estado,String mensaje){
		java.io.StringWriter sw = new StringWriter();
    	MensajeRespuestaDTO respuesta = new MensajeRespuestaDTO();
    	if(estado){
    		respuesta.setEstado("OK");
    	}else{
    		respuesta.setEstado("ERROR");
    	}
    	respuesta.setMensaje(mensaje);
    	JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(MensajeRespuestaDTO.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(respuesta, sw);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw.toString();

    }
	
}
