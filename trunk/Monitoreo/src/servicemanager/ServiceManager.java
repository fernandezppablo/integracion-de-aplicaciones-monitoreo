package servicemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import dto.DespachoDTO;
import dto.ItemRankingDTO;
import dto.MensajeRespuestaDTO;
import interfaces.ServiceManagerInterfaz;

public class ServiceManager implements ServiceManagerInterfaz{
	String ipportal = "localhost:8080"; //IP + PUERTO
	private String targetRanking = "http://" + ipportal + "/PortalWeb/rest/bestSeller/procesar";
	
	@Override
	public MensajeRespuestaDTO enviarRanking(List<ItemRankingDTO> lista) throws Exception {
		try {
			
            URL targetUrl = new URL(targetRanking);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();

            httpConnection.setDoOutput(true);

            httpConnection.setRequestMethod("POST");

            httpConnection.setRequestProperty("Content-Type", "application/json");

            java.io.StringWriter sw = new StringWriter();
            OutputStream outputStream = httpConnection.getOutputStream();
            JAXBContext jc = JAXBContext.newInstance(ItemRankingDTO.class);
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty("eclipselink.media.type", "application/json");
            marshaller.marshal(lista,sw);
            
            outputStream.write(sw.toString().getBytes());

            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "

                    + httpConnection.getResponseCode());

            }

 

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(

                    (httpConnection.getInputStream())));

            String json= "";
            String output = "";
            JAXBContext jaxbcontext;
            while ((output = responseBuffer.readLine()) != null) {
            	json = json + output;
            }
            System.out.println("Texto xml de ranking: " + json);
            
            
            jaxbcontext = JAXBContext.newInstance(MensajeRespuestaDTO.class);
            javax.xml.bind.Unmarshaller desencripta = jaxbcontext.createUnmarshaller();
            MensajeRespuestaDTO mensaje = (MensajeRespuestaDTO) desencripta.unmarshal(new StringReader(json));
            return mensaje;
          } catch (MalformedURLException e) {

            e.printStackTrace();

          } catch (IOException e) {

            e.printStackTrace();

         }


		return null;
	}

	@Override
	public void mandarDespacho(DespachoDTO aMandar) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
