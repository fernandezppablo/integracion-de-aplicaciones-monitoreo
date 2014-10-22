package negocio;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;


@Entity
@XmlRootElement(name="log")
public class ItemAuditoria extends PersistentObject{
	private String log;
	private Date fecha;
	private int idModulo; 
	@ManyToOne
	private Auditoria auditoria;
	@XmlElement(name = "mensaje")
	public String getLog() {
		return log;
	}
	public Auditoria getAuditoria() {
		return auditoria;
	}
	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
	public void setLog(String log) {
		this.log = log;
	}

	@XmlElement(name = "idModulo")
	public int getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(int idModulo) {
		
		this.idModulo = idModulo;
	}
	
	@XmlElement(name = "fecha")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) throws Exception {
		this.fecha = fecha;
	}



	

	
	
	

}
