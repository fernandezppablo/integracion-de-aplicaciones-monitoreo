package interfaces;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import negocio.Auditoria;
@Local
public interface AuditoriaDAOInterfaz {
	public void gravarAuditoria(Auditoria agravar);
}
