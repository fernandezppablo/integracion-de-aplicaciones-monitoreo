package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import negocio.*;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import webservices.WSMonitoreo;

public class test {

	public static void main(String[] args) {
	
		WSMonitoreo monitoreo = new WSMonitoreo();
		FileReader f;
		try {
			f = new FileReader(generate("log.xml"));
			BufferedReader b = new BufferedReader(f);
			String cadena = "";
			String linea;
			while((linea = b.readLine())!=null) {
				cadena = cadena + linea;
			}
			System.out.println(monitoreo.informarLog(cadena));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public static String generate(String name) throws Exception{
		 

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
        Text nodeKeyValue = document.createTextNode("2014-10-05 14:55:59");
        fecha.appendChild(nodeKeyValue);      
        //idModulo
        Element idModulo = document.createElement("idModulo"); 
        Text nodeValueValue = document.createTextNode("5");                
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

	    return temp.getAbsolutePath();
    }

}
