package dto;

import java.io.Serializable;
import java.util.List;

public class ResumenPortalDTO implements Serializable{
	public int id;
	public float total;
	public List<TROrdenVentaDTO> ventas;
}
