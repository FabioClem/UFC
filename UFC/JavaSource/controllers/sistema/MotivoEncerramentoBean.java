package controllers.sistema;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entidades.sistema.MotivoEncerramento;
import models.sistema.MotivoEncerramentoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class MotivoEncerramentoBean {

	private MotivoEncerramento motivoEncerramento;

	private MotivoEncerramento motivoEncerramentoModifica;

	@EJB
	private MotivoEncerramentoServico motivoEncerramentoServico;


	public MotivoEncerramentoBean() {

		this.motivoEncerramento = new MotivoEncerramento();

		this.motivoEncerramentoModifica = new MotivoEncerramento();

	}

	public void cadastrarMotivoEncerramento() {

		try {
			
			this.motivoEncerramentoServico.cadastrarMotivoEncerramento(this.motivoEncerramento);
			JSFUtil.addInfoMessage("Motivo cadastrado com sucesso.");
			this.motivoEncerramento = new MotivoEncerramento();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void modificarMotivoEncerramento() {

		try {

			this.motivoEncerramentoServico.modificarMotivoEncerramento(this.motivoEncerramentoModifica);
			JSFUtil.addInfoMessage("Motivo modificado com sucesso.");			
			this.motivoEncerramentoModifica = new MotivoEncerramento();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public List<MotivoEncerramento> listarMotivoEncerramento() {

		return this.motivoEncerramentoServico.listarMotivoEncerramento();

	}

	public List<MotivoEncerramento> listarMotivoEncerramentoAtivo() {

		return this.motivoEncerramentoServico.listarMotivoEncerramentoAtivo();

	}
	
	public MotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}


	public void setMotivoEncerramento(MotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}


	public MotivoEncerramento getMotivoEncerramentoModifica() {
		return motivoEncerramentoModifica;
	}


	public void setMotivoEncerramentoModifica(MotivoEncerramento motivoEncerramentoModifica) {
		this.motivoEncerramentoModifica = motivoEncerramentoModifica;
	}

}
