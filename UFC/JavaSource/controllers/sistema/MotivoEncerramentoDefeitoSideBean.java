package controllers.sistema;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.sistema.MotivoEncerramentoDefeitoSide;
import models.sistema.MotivoEncerramentoDefeitoSideServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class MotivoEncerramentoDefeitoSideBean {

	private MotivoEncerramentoDefeitoSide motivoEncerramento;

	private MotivoEncerramentoDefeitoSide motivoEncerramentoModifica;

	@EJB
	private MotivoEncerramentoDefeitoSideServico motivoEncerramentoServico;


	public MotivoEncerramentoDefeitoSideBean() {

		this.motivoEncerramento = new MotivoEncerramentoDefeitoSide();

		this.motivoEncerramentoModifica = new MotivoEncerramentoDefeitoSide();

	}

	public void cadastrarMotivoEncerramento() {

		try {
			
			this.motivoEncerramentoServico.cadastrarMotivoEncerramento(this.motivoEncerramento);
			JSFUtil.addInfoMessage("Motivo cadastrado com sucesso.");
			this.motivoEncerramento = new MotivoEncerramentoDefeitoSide();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void modificarMotivoEncerramento() {

		try {

			this.motivoEncerramentoServico.modificarMotivoEncerramento(this.motivoEncerramentoModifica);
			JSFUtil.addInfoMessage("Motivo modificado com sucesso.");			
			this.motivoEncerramentoModifica = new MotivoEncerramentoDefeitoSide();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public List<MotivoEncerramentoDefeitoSide> listarMotivoEncerramento() {

		return this.motivoEncerramentoServico.listarMotivoEncerramento();

	}

	public List<MotivoEncerramentoDefeitoSide> listarMotivoEncerramentoAtivo() {

		return this.motivoEncerramentoServico.listarMotivoEncerramentoAtivo();

	}
	
	public MotivoEncerramentoDefeitoSide getMotivoEncerramento() {
		return motivoEncerramento;
	}


	public void setMotivoEncerramento(MotivoEncerramentoDefeitoSide motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}


	public MotivoEncerramentoDefeitoSide getMotivoEncerramentoModifica() {
		return motivoEncerramentoModifica;
	}


	public void setMotivoEncerramentoModifica(MotivoEncerramentoDefeitoSide motivoEncerramentoModifica) {
		this.motivoEncerramentoModifica = motivoEncerramentoModifica;
	}

}
