package jms;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSServices {
	
	//Data para conectarse por JDNI no se si va a hacer falta
	private String user = "monitoreo";
	private String pass = "monitoreo1.0";
	private InitialContext context = null;
	
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
	
	public void EnviarAuditoriaAModuloEmails(){
		this.InicializarJMS();
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
		
		// ******************************************************************************
		//                     Aca abajo habria que poner el xml.                      //
		// *****************************************************************************
		
		TextMessage message;
		try {
			message = s.createTextMessage("Aca va el mensaje con la data, osea el xml parseado y con toda la magia.");
			mp.send(message);
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("Hasta aca ok.!!!!!");
		
		
		
		
		
		
		
		
	}

}
