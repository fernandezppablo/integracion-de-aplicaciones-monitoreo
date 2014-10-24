package clientes;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import webservices.WSMonitoreo;
import webservices.WSMonitoreoService;






public class ClienteWS {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		WSMonitoreo port = new WSMonitoreoService().getWSMonitoreoPort();
		/************************************/
		/* TEST INFORMAR LOG                */
		/************************************/
		//String resultado = port.informarLog(generateLog("log"));
		/************************************/
		/* TEST REGISTRAR VENTA             */
		/************************************/
		//System.out.println(generateVenta());
		String resultado = port.registrarVenta(generateVenta());
		System.out.println(resultado);
		
		
		
	}
	
	
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public ClienteWS() {
		super();
	
	}
	
	public static String generateLog(String name) throws Exception{
		 

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, name, null);
        document.setXmlVersion("1.0");
 
            //Main Node
        Element raiz = document.getDocumentElement();
        //Por cada key creamos un item que contendr‡ la key y el value

        //Log jefe
        //Element log = document.createElement("log"); 
        //fecha
        Element fecha = document.createElement("fecha"); 
        Text nodeKeyValue = document.createTextNode("1999-12-05 12:04:23");
        fecha.appendChild(nodeKeyValue);      
        //idModulo
        Element idModulo = document.createElement("idModulo"); 
        Text nodeValueValue = document.createTextNode("111");                
        idModulo.appendChild(nodeValueValue);
      //idModulo
        Element mensaje = document.createElement("mensaje"); 
        nodeValueValue = document.createTextNode("Correccion exitosa");                
        mensaje.appendChild(nodeValueValue);
        //append keyNode and valueNode to itemNode
    //    log.appendChild(fecha);
    //    log.appendChild(idModulo);
    //    log.appendChild(mensaje);
        
        //append itemNode to raiz
        raiz.appendChild(fecha); //pegamos el elemento a la raiz "Documento"
        raiz.appendChild(idModulo); //pegamos el elemento a la raiz "Documento"   
        raiz.appendChild(mensaje); //pegamos el elemento a la raiz "Documento"
        //Generate XML
        Source source = new DOMSource(document);
        // Esto sirve para guardar el xml
        //Indicamos donde lo queremos almacenar
        java.io.File temp = new java.io.File(name+".xml");
        Result result = new StreamResult(temp); //nombre del archivo
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
        String cadena = "";
		String linea;
	    FileReader f = new FileReader("log.xml");
	    BufferedReader b = new BufferedReader(f);
	    while((linea = b.readLine())!=null) {
	          cadena = cadena + linea;
	     }
        //
	    
	    return cadena;
    }
	public static String generateVenta() throws Exception{
		 

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, "venta", null);
        document.setXmlVersion("1.0");
 
            //Main Node
        Element raiz = document.getDocumentElement();
        //Por cada key creamos un item que contendr‡ la key y el value

        //Log jefe
        //Element log = document.createElement("log"); 
        //fecha
        Element ventaId = document.createElement("ventaId");
        Text nodeValueValue = document.createTextNode("35");
        ventaId.appendChild(nodeValueValue);
        Element moduloId = document.createElement("moduloId"); 
        nodeValueValue = document.createTextNode("5");
        moduloId.appendChild(nodeValueValue);
        Element cooX = document.createElement("coordenadaX"); 
        nodeValueValue = document.createTextNode("56.45");
        cooX.appendChild(nodeValueValue);
        Element cooY = document.createElement("coordenadaY"); 
        nodeValueValue = document.createTextNode("35.15");
        cooY.appendChild(nodeValueValue);
        Element fecha = document.createElement("fecha"); 
        Text nodeKeyValue = document.createTextNode("1999-12-05 12:04:23");
        fecha.appendChild(nodeKeyValue);
        Element monto = document.createElement("monto"); 
        nodeValueValue = document.createTextNode("50");
        monto.appendChild(nodeValueValue);
        Element ventaItems = document.createElement("ventaItems");
        //Items
        Element item1 = document.createElement("Item");
        Element item1Id = document.createElement("productoId");
        nodeValueValue = document.createTextNode("3");
        item1Id.appendChild(nodeValueValue);
        item1.appendChild(item1Id);
        Element item1Cant = document.createElement("cantidad");
        nodeValueValue = document.createTextNode("10");
        item1Cant.appendChild(nodeValueValue);
        item1.appendChild(item1Cant);
        ventaItems.appendChild(item1);
        Element item2 = document.createElement("Item");
        Element item2Id = document.createElement("productoId");
        nodeValueValue = document.createTextNode("3");
        item2Id.appendChild(nodeValueValue);
        item2.appendChild(item2Id);
        Element item2Cant = document.createElement("cantidad");
        nodeValueValue = document.createTextNode("22");
        item2Cant.appendChild(nodeValueValue);
        item2.appendChild(item2Cant);
        ventaItems.appendChild(item2);

        
        //append itemNode to raiz
        raiz.appendChild(ventaId); //pegamos el elemento a la raiz "Documento"
        raiz.appendChild(moduloId);
        raiz.appendChild(cooX);
        raiz.appendChild(cooY);
        raiz.appendChild(fecha);
        raiz.appendChild(monto);
        raiz.appendChild(ventaItems);
        //Generate XML
        Source source = new DOMSource(document);
        // Esto sirve para guardar el xml
        //Indicamos donde lo queremos almacenar
        java.io.File temp = new java.io.File("venta.xml");
        Result result = new StreamResult(temp); //nombre del archivo
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
        String cadena = "";
		String linea;
	    FileReader f = new FileReader("venta.xml");
	    BufferedReader b = new BufferedReader(f);
	    while((linea = b.readLine())!=null) {
	          cadena = cadena + linea;
	     }
        //
	    
	    return cadena;
    }

}