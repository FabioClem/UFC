package controllers.sistema;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.sistema.MotivoEncerramentoDefeito;
import models.sistema.MotivoEncerramentoDefeitoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class MotivoEncerramentoDefeitoBean {
	
	private MotivoEncerramentoDefeito motivoEncerramentoDefeito;
	
	private MotivoEncerramentoDefeito motivoEncerramentoDefeitoModifica;
	
	@EJB
	private MotivoEncerramentoDefeitoServico motivoEncerramentoDefeitoServico;

	public MotivoEncerramentoDefeitoBean() {
		
		this.motivoEncerramentoDefeito = new MotivoEncerramentoDefeito();
		
		this.motivoEncerramentoDefeitoModifica = new MotivoEncerramentoDefeito();

	}
	
	public void cadastrarMotivoEncerramentoDefeito() {
		
		try {
			
			this.motivoEncerramentoDefeitoServico.cadastrarMotivoEncerramentoDefeito(this.motivoEncerramentoDefeito);
			JSFUtil.addInfoMessage("Sucesso ao cadastrar motivo de encerramento");
			this.motivoEncerramentoDefeito = new MotivoEncerramentoDefeito();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void modificarMotivoEncerramentoDefeito() {
		
		try {
			
			this.motivoEncerramentoDefeitoServico.modificarMotivoEncerramentoDefeito(this.motivoEncerramentoDefeitoModifica);
			JSFUtil.addInfoMessage("Sucesso ao modificar motivo de encerramento");
			this.motivoEncerramentoDefeitoModifica = new MotivoEncerramentoDefeito();
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public List<MotivoEncerramentoDefeito> listarMotivoEncerramentoDefeito() {
		
		return this.motivoEncerramentoDefeitoServico.listarMotivoEncerramentoDefeito();
		
	}
	
	public List<MotivoEncerramentoDefeito> listaMotivoEncerramentoDefeitoAtivo() {
		
		return this.motivoEncerramentoDefeitoServico.listaMotivoEncerramentoDefeitoAtivo();
		
	}

	public MotivoEncerramentoDefeito getMotivoEncerramentoDefeito() {
		return motivoEncerramentoDefeito;
	}

	public void setMotivoEncerramentoDefeito(MotivoEncerramentoDefeito motivoEncerramentoDefeito) {
		this.motivoEncerramentoDefeito = motivoEncerramentoDefeito;
	}

	public MotivoEncerramentoDefeito getMotivoEncerramentoDefeitoModifica() {
		return motivoEncerramentoDefeitoModifica;
	}

	public void setMotivoEncerramentoDefeitoModifica(MotivoEncerramentoDefeito motivoEncerramentoDefeitoModifica) {
		this.motivoEncerramentoDefeitoModifica = motivoEncerramentoDefeitoModifica;
	}	

}
