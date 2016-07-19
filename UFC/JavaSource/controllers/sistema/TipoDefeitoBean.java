package controllers.sistema;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.sistema.TipoDefeito;
import models.sistema.TipoDefeitoServico;

@ManagedBean
@ViewScoped
public class TipoDefeitoBean {

	private TipoDefeito tipoDefeito;
	
	@EJB
	private TipoDefeitoServico tipoDefeitoServico;
	
	public TipoDefeitoBean() {

		this.tipoDefeito = new TipoDefeito();
		
	}
	
	public List<TipoDefeito> listaTipoDefeito() {
		
		return this.tipoDefeitoServico.listarTipoDefeitoAtivo();
		
	}

	public TipoDefeito getTipoDefeito() {
		return tipoDefeito;
	}

	public void setTipoDefeito(TipoDefeito tipoDefeito) {
		this.tipoDefeito = tipoDefeito;
	}
	
	

}
