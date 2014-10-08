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






public class Main {
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		WSMonitoreo port = new WSMonitoreoService().getWSMonitoreoPort();
		String resultado = port.informarLog(generate("log"));
		System.out.println(resultado);
		
		
		/*
		Properties jndiProps = new Properties();
		jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProps.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447");
		jndiProps.put(Context.SECURITY_PRINCIPAL, "test");
		jndiProps.put(Context.SECURITY_CREDENTIALS, "test123");
		jndiProps.put("jboss.naming.client.ejb.context", true);
		Context context = new InitialContext(jndiProps);
		InterfazAuditoria administrarproductos = (InterfazAuditoria)context.lookup("TPIntegracionAplicacionesEAR/TPIntegracionAplicaciones/AdministradorAuditoria!"+InterfazAuditoria.class.getCanonicalName());
		String prueba = administrarproductos.informarLog("HORNO");
		System.out.println(prueba);
		*/
	}
	
	
	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	
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
	    FileReader f = new FileReader("log.xml");
	    BufferedReader b = new BufferedReader(f);
	    while((linea = b.readLine())!=null) {
	          cadena = cadena + linea;
	     }
        //
	    return cadena;
    }

}