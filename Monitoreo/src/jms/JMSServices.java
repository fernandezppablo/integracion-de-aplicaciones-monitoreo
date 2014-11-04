package jms;

import java.io.StringReader;

import interfaces.AuditoriaDAOInterfaz;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.ejb.ActivationConfigProperty;

import dto.AuditoriaDTO;
import dto.ItemAuditoriaDTO;


@MessageDriven(mappedName = "monitoreo",activationConfig =
{
@ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
@ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
@ActivationConfigProperty(propertyName="destination", propertyValue="java:jboss/exported/queue/monitoreo"),
@ActivationConfigProperty(propertyName="ConnectionFactoryName", propertyValue="ConnectionFactory"),
@ActivationConfigProperty(propertyName="MaxPoolSize", propertyValue="1"),
@ActivationConfigProperty(propertyName="MaxMessages", propertyValue="1"),
@ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true")
})
public class JMSServices implements MessageListener  {
	@EJB(name = "AuditoriaDAO")
	private AuditoriaDAOInterfaz daoauditoria;
	@Override
	public void onMessage(Message mp) {
		TextMessage tm = (TextMessage) mp;
		try {
			generarLog(tm.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generarLog(String xml){
		
		
		JAXBContext jaxbcontext;
		try {
			jaxbcontext = JAXBContext.newInstance(ItemAuditoriaDTO.class);
			javax.xml.bind.Unmarshaller desencripta = jaxbcontext.createUnmarshaller();
			ItemAuditoriaDTO item = (ItemAuditoriaDTO) desencripta.unmarshal(new StringReader(xml));
			
			AuditoriaDTO nueva = new AuditoriaDTO();
			nueva.agregarItemAuditoria(item);
			daoauditoria.grabarAuditoria(nueva);


		} catch (JAXBException e) {
			// TODO Auto-generated catch block
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Se guardo el ItemAuditoria.");
		
		
	}
	
	public void recibirMail(String xml){
		
		generarLog(xml);
		
		System.out.println("ItemAuditoria Guardado, funcionalidad para mandar mail no implementada.");
		
	}

	

}
