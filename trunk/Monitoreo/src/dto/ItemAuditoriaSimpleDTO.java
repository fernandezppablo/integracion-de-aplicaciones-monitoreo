package dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemAuditoriaSimpleDTO {
	private String log;
	private String fecha;
	private int idModulo;
	
	public ItemAuditoriaSimpleDTO() {}
	
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setFecha(Date fecha) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setFecha(dateFormat.format(fecha));
	}
	public int getIdModulo() {
		return idModulo;
	}
	public void setIdModulo(int idModulo) {
		this.idModulo = idModulo;
	}

}
