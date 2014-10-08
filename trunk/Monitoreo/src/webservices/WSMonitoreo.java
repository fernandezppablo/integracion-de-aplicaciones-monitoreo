package webservices;

import interfaces.AuditoriaDAOInterfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import negocio.Auditoria;
import negocio.ItemAuditoria;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import daos.AuditoriaDAO;


@Stateless
@WebService
public class WSMonitoreo{
	//test
	@PersistenceContext(unitName = "TP")
	private EntityManager em;
	@EJB(name = "AuditoriaDAO")
	private AuditoriaDAOInterfaz dao;
	@WebMethod
	public String informarLog(String xml) {
		// Vamos a crear un archivo xml para leerlo
		
		File temp = new File("temp.xml");
		try {
			FileOutputStream fop = new FileOutputStream(temp);
			byte[] contentInBytes = xml.getBytes();
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			DocumentBuilderFactory f‡bricaCreadorDocumento = DocumentBuilderFactory.newInstance();
		    DocumentBuilder creadorDocumento = f‡bricaCreadorDocumento.newDocumentBuilder();
		    Document documento = creadorDocumento.parse("temp.xml");
		  //Obtener el elemento ra’z del documento
		    Element raiz = documento.getDocumentElement();
		    //Obtener la lista de nodos
		    Node fechas = raiz.getElementsByTagName("fecha").item(0);
		    String fecha = fechas.getChildNodes().item(0).getNodeValue(); 
		    Node modulos = raiz.getElementsByTagName("idModulo").item(0);
		    String modulo = modulos.getChildNodes().item(0).getNodeValue(); 
		    Node mensajes = raiz.getElementsByTagName("mensaje").item(0);
		    String mensaje = mensajes.getChildNodes().item(0).getNodeValue(); 
		    Auditoria nueva = new Auditoria();
		    em.persist(nueva);
		    ItemAuditoria item = new ItemAuditoria();
		    Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return crearTextoRespuesta(false,"Formato de fecha invalido");
			}
		    item.setFecha(date);
		    item.setIdModulo(Integer.valueOf(modulo));
		    item.setLog(mensaje);
		    item.setAuditoria(nueva);
		    nueva.agregarItemAuditoria(item);
		    //TEST
		    dao.gravarAuditoria(nueva);
			temp.delete();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return crearTextoRespuesta(true,"Carga finalizada correctamente");
	}


	private String crearTextoRespuesta(boolean aprobado,String mensaje){
		String cadena = "";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder;
		
			builder = factory.newDocumentBuilder();
			
	        DOMImplementation implementation = builder.getDOMImplementation();
	        Document document = implementation.createDocument(null, "resultado", null);
	        document.setXmlVersion("1.0");
	 
	            //Main Node
	        Element raiz = document.getDocumentElement();
	        //Por cada key creamos un item que contendr‡ la key y el value
	
	        //Log jefe
	        //Element log = document.createElement("log"); 
	        //fecha
	        Element estado = document.createElement("estado"); 
	        Text nodeKeyValue = null;
	        if(aprobado){
	        	nodeKeyValue = document.createTextNode("OK");
	        }else{
	        	nodeKeyValue = document.createTextNode("ERROR");
	        }
	        estado.appendChild(nodeKeyValue);      
	        //Mensaje
	        Element mensajeresult = document.createElement("mensaje"); 
	        Text nodeValueValue = document.createTextNode(mensaje);                
	        mensajeresult.appendChild(nodeValueValue);
	
	        //append keyNode and valueNode to itemNode
	    //    log.appendChild(fecha);
	    //    log.appendChild(idModulo);
	    //    log.appendChild(mensaje);
	        
	        //append itemNode to raiz
	        raiz.appendChild(estado); //pegamos el elemento a la raiz "Documento"
	        raiz.appendChild(mensajeresult); //pegamos el elemento a la raiz "Documento"   
	        //Generate XML
	        Source source = new DOMSource(document);
	        // Esto sirve para guardar el xml
	        //Indicamos donde lo queremos almacenar
	        java.io.File temp = new java.io.File("temp2.xml");
	        Result result = new StreamResult(temp); //nombre del archivo
	        Transformer transformer;
		
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			String linea;
			FileReader f = new FileReader("temp2.xml");
			BufferedReader b = new BufferedReader(f);
			while((linea = b.readLine())!=null) {
				cadena = cadena + linea;
			}
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //
		catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cadena;
	}
	
}
