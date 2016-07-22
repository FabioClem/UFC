package controllers.sistema;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import controllers.LoginBean;
import entidades.sistema.DefeitoSide;
import entidades.sistema.Lote;
import entidades.sistema.StatusDefeito;
import models.sistema.DefeitoSideServico;
import models.sistema.LoteServico;
import models.sistema.StatusDefeitoServico;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class LoteBean {
	
	@ManagedProperty(value="#{loginBean}")
	private LoginBean sessao;
	
	private Lote lote;
	
	@EJB
	private LoteServico loteServico;
	
	@EJB
	private StatusDefeitoServico statusDefeitoServico;
	
	@EJB
	private DefeitoSideServico defeitoSideServico;
	
	public LoteBean() {
		
		this.lote = new Lote();
		
	}
		
	public void cadastrarLote(FileUploadEvent event) {
				
		UploadedFile file = event.getFile();
		
		try {
			
			StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Aberto");
			this.loteServico.cadastrarLote(file, sessao.getUsuario(), statusDefeito);
			JSFUtil.addInfoMessage("Lote cadastrado com sucesso.");
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());

		}		
		
	}
	
	public void loteDefeitoSide(FileUploadEvent event) {
		
		UploadedFile file = event.getFile();
		
		try {
						
			this.loteServico.loteDefeitoSide(file, this.sessao.getUsuario());
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void listarLoteEspecificoNome() {
		
		try {
					
			this.lote = this.loteServico.listarLoteEspecificoNome(this.lote);
			JSFUtil.addInfoMessage("Lote Carregado com sucesso.");
			
		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());
			
		}
		
	}
	
	public void removerLoteEspecifico() throws Exception {
		
		this.listarLoteEspecificoNome();
		
		StatusDefeito statusDefeitoAberto = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Aberto");
		
		List<DefeitoSide> listaDeDefeitoSide = this.defeitoSideServico.listarDefeitoSideDeLoteEspecifico(this.lote, statusDefeitoAberto);
		
		StatusDefeito statusDefeitoRemovido = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Removido Fila");
		
		for (DefeitoSide defeitoSide : listaDeDefeitoSide) {
			
			this.defeitoSideServico.modificarDefeitoSide(defeitoSide, statusDefeitoRemovido);
			
		}
		
		JSFUtil.addInfoMessage("Defeitos do lote foram removidos com sucesso.");
				
	}

	public LoginBean getSessao() {
		return sessao;
	}

	public void setSessao(LoginBean sessao) {
		this.sessao = sessao;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}
	
}
