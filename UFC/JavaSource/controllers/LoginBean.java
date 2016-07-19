package controllers;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entidades.ControleUsuario;
import entidades.UsuarioEfika;
import models.ControleUsuarioServico;
import models.LoginServico;
import util.JSFUtil;
import webservices.Usuario;

import java.io.IOException;
import java.io.Serializable;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginBean implements Serializable{

	private UsuarioEfika usuario;

	private Usuario usuarioWS;

	private String senha;

	private String pagina;

	@EJB
	private LoginServico servicoLogin;

	@EJB
	private ControleUsuarioServico controleUsuarioServico;

	private boolean logado;

	public LoginBean() {
		this.usuario = new UsuarioEfika();
		this.logado = false;
	}

	public void validarLogin() {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try {

			if (!this.logado) {

				context.redirect("/UFC/index.jsf");

			}			

		} catch (IOException e) {

			e.printStackTrace();

		}		

	}

	public Boolean isAtivo() {


		try {
			
			return this.controleUsuarioServico.usuarioAtivo(this.usuario);
						
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());
			return false;
			
		}

	}

	public Boolean isAdm() {

		try {

			ControleUsuario controleUsuario = this.controleUsuarioServico.listarControleUsuarioEspecifico(this.usuario);

			return controleUsuario.getAdm();

		} catch (Exception e) {

			return false;

		}

	}

	public void isAdmRedireciona() {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try {

			ControleUsuario controleUsuario = this.controleUsuarioServico.listarControleUsuarioEspecifico(this.usuario);

			if (!controleUsuario.getAdm()) {

				context.redirect("/UFC/index.jsf");

			}

		} catch (Exception e) {

			JSFUtil.addErrorMessage("Você não é administrador.");

		}

	}

	public Boolean validaTipoDefeito(String sistema) {

		ControleUsuario controleUsuario = new ControleUsuario();

		String[] tipoDefeito;

		Boolean esAdm = false;

		try {

			controleUsuario = this.servicoLogin.validaListaAdm(this.usuario);

			tipoDefeito = controleUsuario.getTipoDefeito().split(";");

			for (String string : tipoDefeito) {

				if (string.equalsIgnoreCase(sistema)) {

					esAdm = true;

				}

			}

			return esAdm;

		} catch (Exception e) {

			return esAdm;

		}

	}

	public String logar() {

		try {
			
			this.usuario.setLogin(this.usuario.getLogin().toUpperCase());

			this.usuarioWS = this.servicoLogin.buscaLoginWS(this.usuario.getLogin());
			this.servicoLogin.autenticaLogin(this.usuarioWS, this.senha);

			this.logado = true;			
			return "index.jsf"; 

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			this.usuario = new UsuarioEfika();
			return "";

		}

	}

	public void deslogar() {

		this.usuario = new UsuarioEfika();
		this.logado = false;

	}

	public void validaPagina(String pagina) {

		this.pagina = pagina;

	}

	public UsuarioEfika getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEfika usuario) {
		this.usuario = usuario;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioWS() {
		return usuarioWS;
	}

	public void setUsuarioWS(Usuario usuarioWS) {
		this.usuarioWS = usuarioWS;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

}
