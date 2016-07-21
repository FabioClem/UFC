package controllers.sistema;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import models.sistema.TipificacaoServico;

@ManagedBean
@ViewScoped
public class TipificacaoBean {
		
	@EJB
	private TipificacaoServico tipificacaoServico;
	

	public TipificacaoBean() {
		
		
		
	}
	

}
