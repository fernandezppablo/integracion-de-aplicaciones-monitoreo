package jms;

import interfaces.AuditoriaDAOInterfaz;
import interfaces.TROrdenVentaDAOInterfaz;

import java.io.StringReader;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import dto.AuditoriaDTO;
import dto.ItemAuditoriaDTO;


public class JMSServices {
	
	//Data para conectarse por JDNI no se si va a hacer falta
	private String user = "monitoreo";
	private String pass = "monitoreo1.0";
	private InitialContext context = null;
	
	@EJB(name = "AuditoriaDAO")
	private AuditoriaDAOInterfaz daoauditoria;
	
	public void InicializarJMS() {
		
		//Propiedas de coneccion
		Properties config = new Properties();
		config.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		config.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		config.put(Context.PROVIDER_URL, "remote://localhost:4447");
		config.put(Context.SECURITY_PRINCIPAL, user);
		config.put(Context.SECURITY_CREDENTIALS, pass);
		
		//Instanciacion del contexto inicial
		try {
			context = new InitialContext(config);
			
		} catch (NamingException e) {
			System.out.println("Error al inicializar el contexto!");
			e.printStackTrace();
			return;
		}
		System.out.println("Contexto inicializado correctamente!");
		

	}
	//TEST //////////////////////////////////////
	public void PonerXmlEnCola(String xml){

		ConnectionFactory cf = null;
		Queue q = null;
		Connection c = null;
		Session s = null;
		MessageProducer mp = null;
		//Buscamos la connection factory y la cola
		try {
			cf = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			q = (Queue)  context.lookup("queue/monitoreo");
		
			
		} catch (NamingException e) {
			System.out.println("Error al buscar la connection factory y la cola.");
			e.printStackTrace();
		}
		//Crear la coneccion
		try {
			c = cf.createConnection(user,pass);
		} catch (JMSException e) {
			System.out.println("Error al crear la coneccion.");
			e.printStackTrace();
		}
		//Crear la sesion
		try {
			s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			System.out.println("Error al crear la sesion");
			e.printStackTrace();
		}
		//Crear el productor de mensajes
		try {
			mp = s.createProducer(q);
		} catch (JMSException e) {
			System.out.println("Error al crear el productor de mensajes.");
			e.printStackTrace();
		}
		//Iniciar la coneccion
		try {
			c.start();
		} catch (JMSException e) {
			System.out.println("Error al iniciar la coneccion");
			e.printStackTrace();
		}
		//Mandar un mensaje
		
		
		
		TextMessage message;
		try {
			message = s.createTextMessage(xml);
			mp.send(message);
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Se puso el xml en la cola.");
		
		//TODO PROBAR!!!!
		
		
	}
	
	//TODO Recibir log portal web
	//TODO Recibir log despacho (SON LO MISMO ES UN SOLO METODO)
	
	public void generarLog(String xml){
		this.InicializarJMS();
		ConnectionFactory cf = null;
		Queue q = null;
		Connection c = null;
		Session s = null;
		MessageConsumer mp = null;
		//Buscamos la connection factory y la cola
		try {
			cf = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			q = (Queue)  context.lookup("queue/monitoreo");
		
			
		} catch (NamingException e) {
			System.out.println("Error al buscar la connection factory y la cola.");
			e.printStackTrace();
		}
		//Crear la coneccion
		try {
			c = cf.createConnection(user,pass);
		} catch (JMSException e) {
			System.out.println("Error al crear la coneccion.");
			e.printStackTrace();
		}
		//Crear la sesion
		try {
			s = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			System.out.println("Error al crear la sesion");
			e.printStackTrace();
		}
		//Crear el productor de mensajes
		try {
			mp = s.createConsumer(q);
		} catch (JMSException e) {
			System.out.println("Error al crear el productor de mensajes.");
			e.printStackTrace();
		}
		//Iniciar la coneccion
		try {
			c.start();
		} catch (JMSException e) {
			System.out.println("Error al iniciar la coneccion");
			e.printStackTrace();
		}
		//Mandar un mensaje
		
		
		
		TextMessage message = null;
		try {
			message = (TextMessage) mp.receive();
			
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
		JAXBContext jaxbcontext;
		try {
			jaxbcontext = JAXBContext.newInstance(ItemAuditoriaDTO.class);
			javax.xml.bind.Unmarshaller desencripta = jaxbcontext.createUnmarshaller();
			ItemAuditoriaDTO item = (ItemAuditoriaDTO) desencripta.unmarshal(new StringReader(message.getText()));
			
			AuditoriaDTO nueva = new AuditoriaDTO();
			nueva.agregarItemAuditoria(item);
			daoauditoria.grabarAuditoria(nueva);


		} catch (JAXBException e) {
			// TODO Auto-generated catch block
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Se guardo el xml.");
		
		
	}

}
