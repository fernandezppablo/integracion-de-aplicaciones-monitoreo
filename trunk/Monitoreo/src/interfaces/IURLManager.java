package interfaces;

import javax.ejb.Local;

import enums.IP;
import negocio.URLContainer;

@Local
public interface IURLManager {
	public URLContainer getIPs();
	public void setIP(String ip, IP type);
}
