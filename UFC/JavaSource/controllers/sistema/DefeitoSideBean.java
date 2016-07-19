package controllers.sistema;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import controllers.LoginBean;
import entidades.sistema.DefeitoSide;
import entidades.sistema.StatusDefeito;
import entidades.sistema.TipoDefeito;
import models.sistema.DefeitoServico;
import models.sistema.DefeitoSideServico;
import models.sistema.StatusDefeitoServico;
import models.sistema.TipoDefeitoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class DefeitoSideBean {

	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;

	private DefeitoSide defeitoSide;

	private DefeitoSide defeitoSideModifica;

	@EJB
	private DefeitoSideServico defeitoSideServico;

	@EJB
	private DefeitoServico defeitoServico;

	@EJB
	private StatusDefeitoServico statusDefeitoServico;

	@EJB
	private TipoDefeitoServico tipoDefeitoServico;

	public DefeitoSideBean() {

		this.defeitoSide = new DefeitoSide();

		this.defeitoSideModifica = new DefeitoSide();

	}

	public void assumirDefeitoSide(DefeitoSide defeitoSideA) {

		try {

			StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Em tratamento");			
			this.defeitoSideServico.assumirDefeitoSide(defeitoSideA, statusDefeito, this.sessao.getUsuario());
			this.redirecionaDetalhesDefeitoSide(defeitoSideA);
			JSFUtil.addInfoMessage("Defeito " + defeitoSideA.getInstancia() + "Associdado com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void modificarDefeitoSide() {

		try {

			TipoDefeito tipoDefeito = this.tipoDefeitoServico.listarTipoDefeitoEspecifico("Pro-Ativo");		

			this.defeitoServico.cadastrarDefeito(this.defeitoSideModifica, tipoDefeito);

			JSFUtil.addInfoMessage("Defeito modificado com sucesso.");

			this.defeitoSide = new DefeitoSide();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void encerrarDefeitoSide() {

		try {

			StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Encerrado");			
			this.defeitoSideServico.modificarDefeitoSide(this.defeitoSideModifica, statusDefeito);

			this.redirecionaParaPaginaMeusDefeitos();
			JSFUtil.addInfoMessage("Defeito modificado com sucesso.");
			this.defeitoSide = new DefeitoSide();

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void modificarDefeitoSideRemoveLista() {

		try {

			StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Removido Fila");			
			this.defeitoSideServico.modificarDefeitoSide(this.defeitoSide, statusDefeito);
			this.redirecionaParaPaginaMeusDefeitos();
			JSFUtil.addInfoMessage("Ação realizada com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}
	
	public void modificarDefeitoSideRemoveLista(DefeitoSide defeitoSide) {

		try {

			StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Removido Fila");			
			this.defeitoSideServico.modificarDefeitoSide(defeitoSide, statusDefeito);
			JSFUtil.addInfoMessage("Ação realizada com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void redirecionaDetalhesDefeitoSide(DefeitoSide defeitoSide) {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try {

			context.redirect(context.getRequestContextPath() + "/sistema/detalhesDefeito.jsf?id=" + defeitoSide.getId());

		} catch (IOException e) {

			e.printStackTrace();

		}		

	}

	public void listarDefeitoSideEspecifico() {

		try {

			this.defeitoSide = this.defeitoSideServico.listarDefeitoSideEspecifico(this.defeitoSide, this.sessao.getUsuario());

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			this.redirecionaParaPaginaRestrito();

		}

	}

	public List<DefeitoSide> listarMeusDefeitos() throws Exception {		

		return this.defeitoSideServico.listarMeusDefeitos(this.sessao.getUsuario(), this.statusDefeitoServico.listarStatusDefeitoEspecifico("Em tratamento"));

	}

	public void redirecionaParaPaginaRestrito() {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try {

			context.redirect(context.getRequestContextPath() + "/restrito.jsf");

		} catch (IOException e) {

			e.printStackTrace();

		}	
	}

	public void redirecionaParaPaginaMeusDefeitos() {

		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		try {

			context.redirect(context.getRequestContextPath() + "/sistema/meusDefeitos.jsf");

		} catch (IOException e) {

			e.printStackTrace();

		}	
	}

	public List<DefeitoSide> listarDefeitoSideEncerradoSemSS() throws Exception {

		StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Encerrado");

		return this.defeitoSideServico.listarDefeitoSideEncerradoSemSS(statusDefeito);

	}

	public List<DefeitoSide> listarDefeitoSideEmTratamento() throws Exception {


		StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Em tratamento");	

		return this.defeitoSideServico.listarDefeitoSide(statusDefeito);		

	}
	
	public void voltarDefeitoParaFila(DefeitoSide defeitoSide) {
		
		try {
			
			StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Aberto");
			
			defeitoSide.setUsuarioEfika(null);
			
			this.defeitoSideServico.modificarDefeitoSide(defeitoSide, statusDefeito);
			
			JSFUtil.addInfoMessage("Erro ao voltar defeito para fila");
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}

	public DefeitoSide getDefeitoSide() {
		return defeitoSide;
	}

	public void setDefeitoSide(DefeitoSide defeitoSide) {
		this.defeitoSide = defeitoSide;
	}

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}

	public DefeitoSide getDefeitoSideModifica() {
		return defeitoSideModifica;
	}

	public void setDefeitoSideModifica(DefeitoSide defeitoSideModifica) {
		this.defeitoSideModifica = defeitoSideModifica;
	}

}
