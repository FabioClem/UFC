package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import entidades.ControleUsuario;
import entidades.UsuarioEfika;
import models.ControleUsuarioServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class ControleUsuarioBean {

	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;

	private ControleUsuario controleUsuario;

	private ControleUsuario controleUsuarioModifica;
	
	private List<ControleUsuario> tabelaFiltrada;

	private List<String> listaDoTipoDefeito;

	private List<String> listaDoTipoDefeitoModifica;
	
	private List<String> listaDeTipificacao;

	@EJB
	private ControleUsuarioServico controleUsuarioServico;

	public ControleUsuarioBean() {

		this.controleUsuario = new ControleUsuario();

		this.controleUsuarioModifica = new ControleUsuario();

		this.listaDoTipoDefeito = new ArrayList<String>();

		this.listaDoTipoDefeitoModifica = new ArrayList<String>();

	}

	public void cadastrarControleUsuario() {

		try {
			
			this.controleUsuarioServico.cadastrarControleUsuario(this.controleUsuario, this.listaDoTipoDefeito);
			JSFUtil.addInfoMessage("Cadastrado com sucesso");			
			this.controleUsuario = new ControleUsuario();
			this.listaDoTipoDefeito = new ArrayList<String>();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void validaCadastroControleUsuario() {

		try {

			this.controleUsuarioServico.listarControleUsuarioEspecifico(this.controleUsuario.getUsuarioEfika());
			JSFUtil.addInfoMessage("Usuário ja possui controle cadastrado, por favor modifique-o na lista.");

		} catch (Exception e) {

			this.cadastrarControleUsuario();

		}

	}

	public void modificarControleUsuario() {

		try {

			this.controleUsuarioServico.modificarControleUsuario(this.controleUsuarioModifica, this.listaDoTipoDefeitoModifica);
			JSFUtil.addInfoMessage("Controle modificado com sucesso.");
			this.controleUsuarioModifica = new ControleUsuario();
			this.listaDoTipoDefeitoModifica = new ArrayList<String>();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public List<UsuarioEfika> listarUsuarioEfika() {

		return this.controleUsuarioServico.listarUsuarioEfika();

	}

	public List<ControleUsuario> listarControleUsuario(Boolean adm) {

		return this.controleUsuarioServico.listarControleUsuario(adm);

	}

	public Boolean validaControleUsuario(String tipo) {

		return this.controleUsuarioServico.validaControleUsuario(this.sessao.getUsuario(), tipo);

	}

	public ControleUsuario getControleUsuario() {
		return controleUsuario;
	}

	public void setControleUsuario(ControleUsuario controleUsuario) {
		this.controleUsuario = controleUsuario;
	}

	public ControleUsuario getControleUsuarioModifica() {
		return controleUsuarioModifica;
	}

	public void setControleUsuarioModifica(ControleUsuario controleUsuarioModifica) {
		this.controleUsuarioModifica = controleUsuarioModifica;
	}

	public List<String> getListaDoTipoDefeito() {
		return listaDoTipoDefeito;
	}

	public void setListaDoTipoDefeito(List<String> listaDoTipoDefeito) {
		this.listaDoTipoDefeito = listaDoTipoDefeito;
	}

	public List<String> getListaDoTipoDefeitoModifica() {
		return listaDoTipoDefeitoModifica;
	}

	public void setListaDoTipoDefeitoModifica(List<String> listaDoTipoDefeitoModifica) {
		this.listaDoTipoDefeitoModifica = listaDoTipoDefeitoModifica;
	}

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}

	public List<ControleUsuario> getTabelaFiltrada() {
		return tabelaFiltrada;
	}

	public void setTabelaFiltrada(List<ControleUsuario> tabelaFiltrada) {
		this.tabelaFiltrada = tabelaFiltrada;
	}

	public List<String> getListaDeTipificacao() {
		return listaDeTipificacao;
	}

	public void setListaDeTipificacao(List<String> listaDeTipificacao) {
		this.listaDeTipificacao = listaDeTipificacao;
	}	

}
