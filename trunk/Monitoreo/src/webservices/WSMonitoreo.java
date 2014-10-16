package webservices;

import interfaces.AuditoriaDAOInterfaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import negocio.Articulo;
import negocio.Auditoria;
import negocio.ItemAuditoria;
import negocio.ItemOrdenVenta;
import negocio.TROrdenVenta;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import daos.AuditoriaDAO;
import enums.Estado;


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
		
		try {
			DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
		    DocumentBuilder creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
		    InputSource beta = new InputSource();
		    beta.setCharacterStream(new StringReader(xml));
		    Document documento = creadorDocumento.parse(beta);
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
		    dao.grabarAuditoria(nueva);
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

	/************************************************/
	/* SERVICIO QUE RECIBE UNA VENTA Y LA GUARDA,   */
	/* NO ASOCIA A LA VENTA NINGUNA ORDEN DE        */
	/* TABLAS (INSERT): TRORDENVENTA,ITEMORDENVENTA */
	/* TABLAS (UPDATE): ARTICULO (CDAD VENTAS)      */
	/************************************************/
	@WebMethod
	public String registrarVenta(String xml)
	{
		//FLAGS
	    String mensajeFinal="Venta registrada";
	    boolean aprobado=true;
		//Creo el archivo xml para parsear la venta
			try {
				DocumentBuilderFactory fabricaCreadorDocumento = DocumentBuilderFactory.newInstance();
			    DocumentBuilder creadorDocumento;
			    creadorDocumento = fabricaCreadorDocumento.newDocumentBuilder();
			    InputSource beta = new InputSource();
			    beta.setCharacterStream(new StringReader(xml));
			    Document documento = creadorDocumento.parse(beta);
		
				
				//PARSEO
			    Element raiz = documento.getDocumentElement();
			    //Obtener la lista de nodos
			    Node ventaIdN = raiz.getElementsByTagName("ventaId").item(0);
			    Integer ventaId = Integer.valueOf(ventaIdN.getChildNodes().item(0).getNodeValue());
			    Node moduloIdN = raiz.getElementsByTagName("moduloId").item(0);
			    Integer moduloId = Integer.valueOf(moduloIdN.getChildNodes().item(0).getNodeValue());
			    Node latitudN = raiz.getElementsByTagName("coordenadaX").item(0);
			    Float latitud = Float.valueOf(latitudN.getChildNodes().item(0).getNodeValue());
			    Node longitudN = raiz.getElementsByTagName("coordenadaY").item(0);
			    Float longitud = Float.valueOf(longitudN.getChildNodes().item(0).getNodeValue());
			    Node fechaN = raiz.getElementsByTagName("fecha").item(0);
			    String fecha = fechaN.getChildNodes().item(0).getNodeValue();
			    Node montoN = raiz.getElementsByTagName("monto").item(0);
			    Float monto = Float.valueOf(montoN.getChildNodes().item(0).getNodeValue());
			    Node ventasItemN = raiz.getElementsByTagName("ventaItems").item(0);
			    List<ItemOrdenVenta> items= new ArrayList<ItemOrdenVenta>();
			    for(int i = 0 ; i<ventasItemN.getChildNodes().getLength()/2;i++)
			    {
			    	Node productoIdN = raiz.getElementsByTagName("productoId").item(i);
				    Integer productoId =Integer.valueOf(productoIdN.getChildNodes().item(0).getNodeValue());
				    Node cantidadN = raiz.getElementsByTagName("cantidad").item(i);
				    Float cantidad = Float.valueOf(cantidadN.getChildNodes().item(0).getNodeValue());
				    ItemOrdenVenta iov = new ItemOrdenVenta();
				    Articulo art=dao.traerArticuloPorCodigo(productoId);
				    //chequear si art es null
				    if(art==null)
				    {
				    	mensajeFinal="El articulo de codigo "+ String.valueOf(productoId)+ " no existe";
				    	aprobado=false;
				    	i=ventasItemN.getChildNodes().getLength()/2;
				    }else{
				    	iov.setArticulo(art);
				    	iov.setCantidad(cantidad);
				    	items.add(iov);
				    }
			    }
			    
			    if(aprobado)
			    {
			    	TROrdenVenta trov = new TROrdenVenta();
			    	trov.setEstado(Estado.Abierta);
			    	
			    	trov.setNumero(ventaId);
			    	trov.setLongitud(longitud);
			    	trov.setLatitud(latitud);
			    	trov.setMonto(monto);
			    	trov.setFecha(fecha);
			    	trov.setItems(items);
			    	dao.grabarVenta(trov);
			    	
			    }
			
			} catch (FileNotFoundException e) {
				aprobado=false;
				mensajeFinal="El archivo xml no ha podido ser creado";
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				aprobado=false;
				mensajeFinal="Error creando el archivo";
				//e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				aprobado=false;
				mensajeFinal="Ha surgido un problema durante el parseo";
				//e.printStackTrace();
			}catch (SAXException e) {
				// TODO Auto-generated catch block
				aprobado=false;
				mensajeFinal="SAX Error";
				//e.printStackTrace();
			}
			return crearTextoRespuesta(aprobado,mensajeFinal);
		
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
	        
	        Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        StringWriter sw = new StringWriter();
	        transformer.transform(new DOMSource(document), new StreamResult(sw));
			cadena = sw.toString();
	
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cadena;
	}
	
}
