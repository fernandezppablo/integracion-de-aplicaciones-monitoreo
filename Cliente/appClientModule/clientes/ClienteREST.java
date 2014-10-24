package clientes;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;

import sun.net.www.URLConnection;


public class ClienteREST {
	
	private static final String targetURL = "http://localhost:8080/Monitoreo/Web/REST/cambioEstadoDespacho";
	
	public static void main(String[] args) throws IOException {
		JSONObject objeto = new JSONObject();
		objeto.put("despachoId", 23455);

		try {
		
            URL targetUrl = new URL(targetURL);

            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();

            httpConnection.setDoOutput(true);

            httpConnection.setRequestMethod("POST");

            httpConnection.setRequestProperty("Content-Type", "application/json");

            String input = objeto.toJSONString();

            OutputStream outputStream = httpConnection.getOutputStream();

            outputStream.write(input.getBytes());

            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "

                    + httpConnection.getResponseCode());

            }

 

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(

                    (httpConnection.getInputStream())));

 

            String output;
            System.out.println("Output from Server:\n");

            while ((output = responseBuffer.readLine()) != null) {

                System.out.println(output);

            }

            httpConnection.disconnect();
          } catch (MalformedURLException e) {

            e.printStackTrace();

          } catch (IOException e) {

            e.printStackTrace();

         }


	}

}
