package controllers.sistema;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.sistema.ComentarioDefeito;
import entidades.sistema.Defeito;
import entidades.sistema.DefeitoSide;
import models.sistema.ComentarioDefeitoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class ComentarioDefeitoBean {

	private ComentarioDefeito comentarioDefeito;
		
	@EJB
	private ComentarioDefeitoServico comentarioDefeitoServico;
	
	public ComentarioDefeitoBean() {
		
		this.comentarioDefeito = new ComentarioDefeito();
		
		
	}
	
	public void cadastrarComentarioDefeito(DefeitoSide defeitoSide) {
		
		try {
			
			this.comentarioDefeitoServico.cadastrarComentarioDefeito(this.comentarioDefeito, defeitoSide);
			JSFUtil.addInfoMessage("Comentário cadastrado com sucesso.");
			this.comentarioDefeito = new ComentarioDefeito();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void cadastrarComentarioDefeito(Defeito defeito) {
		
		try {
			
			this.comentarioDefeitoServico.cadastrarComentarioDefeito(this.comentarioDefeito, defeito);
			JSFUtil.addInfoMessage("Comentário cadastrado com sucesso.");
			this.comentarioDefeito = new ComentarioDefeito();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public List<ComentarioDefeito> listarComentarioDefeito(DefeitoSide defeitoSide) {
		
		return this.comentarioDefeitoServico.listarComentarioDefeito(defeitoSide);
		
	}

	public ComentarioDefeito getComentarioDefeito() {
		return comentarioDefeito;
	}

	public void setComentarioDefeito(ComentarioDefeito comentarioDefeito) {
		this.comentarioDefeito = comentarioDefeito;
	}

}
