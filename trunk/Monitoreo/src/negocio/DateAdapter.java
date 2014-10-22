package negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	   private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	   public Date unmarshal(final String xml) throws Exception {
	      return this.dateFormat.parse(xml);
	   }

	   public String marshal(final Date object) throws Exception {
	      return this.dateFormat.format(object);
	   }

	}