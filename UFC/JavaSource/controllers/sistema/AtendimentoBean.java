package controllers.sistema;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import entidades.sistema.DefeitoSide;
import entidades.sistema.StatusDefeito;
import models.sistema.DefeitoSideServico;
import models.sistema.StatusDefeitoServico;
import util.JSFUtil;

@ManagedBean
@ApplicationScoped
@Singleton
public class AtendimentoBean {
		
	public List<DefeitoSide> listaDeDefeitoSide;
	
	Timer timerBuscaDefeitosAtivo = new Timer();
	
	TimerTask buscaDefeitosAtivo = new TimerTask() {
		
		@Override
		public void run() {			
			
			buscarDefeitosSideAtivo();
			
		}
		
	};
	
	@EJB
	private DefeitoSideServico defeitoSideServico;
	
	@EJB
	private StatusDefeitoServico statusDefeitoServico;
	
	@PostConstruct
	public void init() {
		
		this.buscarDefeitosSideAtivo();
		
		timerBuscaDefeitosAtivo.schedule(buscaDefeitosAtivo, 15000, 15000);
		
	}

	public AtendimentoBean() {	
		
	}
	
	public void buscarDefeitosSideAtivo() {
		
		try {
			
			StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Aberto");
			listaDeDefeitoSide = this.defeitoSideServico.listarDefeitoSide(statusDefeito);
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());

		}		
		
	}
	
	public void removerDefeitoSideAoAssumir(DefeitoSide defeitoSide) {
		
		try {
			
			defeitoSide = this.defeitoSideServico.listarDefeitoSideEspecifico(defeitoSide);
			
			StatusDefeito statusDefeito = defeitoSide.getStatusDefeito();
			
			if (statusDefeito.getNome().equalsIgnoreCase("Em tratamento")) {
				
				this.listaDeDefeitoSide.remove(defeitoSide);
				
			}
			
			this.buscarDefeitosSideAtivo();
			
		} catch (Exception e) {
			
			JSFUtil.addErrorMessage(e.getMessage());

		}
		
	}

	public List<DefeitoSide> getListaDeDefeitoSide() {
		return listaDeDefeitoSide;
	}

	public void setListaDeDefeitoSide(List<DefeitoSide> listaDeDefeitoSide) {
		this.listaDeDefeitoSide = listaDeDefeitoSide;
	}

}
