package webservices.clientsample;

import webservices.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        WSMonitoreoService service1 = new WSMonitoreoService();
	        System.out.println("Create Web Service...");
	        WSMonitoreo port1 = service1.getWSMonitoreoPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.registrarVenta(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.enviarInformeAuditoria(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.generarRespuesta(Boolean.parseBoolean(args[0]),null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        WSMonitoreo port2 = service1.getWSMonitoreoPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.registrarVenta(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.enviarInformeAuditoria(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.generarRespuesta(Boolean.parseBoolean(args[1]),null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
