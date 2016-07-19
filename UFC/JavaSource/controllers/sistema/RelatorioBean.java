package controllers.sistema;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import entidades.sistema.DefeitoSide;
import entidades.sistema.MotivoEncerramento;
import entidades.sistema.StatusDefeito;
import entidades.sistema.Tipificacao;
import models.sistema.DefeitoSideServico;
import models.sistema.MotivoEncerramentoServico;
import models.sistema.StatusDefeitoServico;
import models.sistema.TipificacaoServico;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class RelatorioBean implements Serializable {
	
	private List<DefeitoSide> defeitoSides;
	
	private Date dataInicio;
	
	private Date dataFim;
	
	private PieChartModel graficoDefeitoSide;
	
	private PieChartModel graficoDefeitoSideMotivoEncerramento;
	
	private PieChartModel graficoDefeitoSideTipificacao; 

	@EJB
	private DefeitoSideServico defeitoSideServico;
	
	@EJB
	private StatusDefeitoServico statusDefeitoServico;
	
	@EJB
	private MotivoEncerramentoServico motivoEncerramentoServico;
	
	@EJB
	private TipificacaoServico tipificacaoServico;
	
	@PostConstruct
	public void init() {
		
		this.criarGraficos();
		
	}
	
	public RelatorioBean() {		
		
	}
	
	public void criarGraficos() {
		
		this.graficoDefeitoSide();
		this.graficoDefeitoSideMotivoEncerramento();
		this.graficoDefeitoSideTipificacao();
		
	}
	
	public void graficoDefeitoSide() {
		
		this.graficoDefeitoSide = new PieChartModel();
		
		List<StatusDefeito> listaDeStatus = this.statusDefeitoServico.listarStatusDefeitoAtivo();
		
		for (StatusDefeito statusDefeito : listaDeStatus) {
			
			Integer total = this.defeitoSideServico.listarDefeitoSideStatus(statusDefeito).size();
			
			this.graficoDefeitoSide.set(statusDefeito.getNome(), total);
			
		}	
		
		this.graficoDefeitoSide.setSeriesColors("003245, 004356, 005466, 006476, 007486, 008597, 0095A7, 005B7, 0086C7, 00C6D7");
		this.graficoDefeitoSide.setTitle("Defeitos Pró-Ativo ");
		this.graficoDefeitoSide.setLegendPosition("sw");
		this.graficoDefeitoSide.setShowDataLabels(true);
		
	}
	
	public void graficoDefeitoSideMotivoEncerramento() {
		
		this.graficoDefeitoSideMotivoEncerramento = new PieChartModel();
		
		List<MotivoEncerramento> listaDeMotivoEncerramento = this.motivoEncerramentoServico.listarMotivoEncerramentoAtivo();
		
		for (MotivoEncerramento motivoEncerramento : listaDeMotivoEncerramento) {
			
			Integer total = this.defeitoSideServico.listarDefeitoSideMotivoEncerramento(motivoEncerramento).size();
			
			this.graficoDefeitoSideMotivoEncerramento.set(motivoEncerramento.getNome(), total);
			
		}
		
		this.graficoDefeitoSideMotivoEncerramento.setSeriesColors("003245, 004356, 005466, 006476, 007486, 008597, 0095A7, 005B7, 0086C7, 00C6D7");
		this.graficoDefeitoSideMotivoEncerramento.setTitle("Motivo de encerramento Pró-Ativo");
		this.graficoDefeitoSideMotivoEncerramento.setLegendPosition("sw");
		this.graficoDefeitoSideMotivoEncerramento.setShowDataLabels(true);
		
	}
	
	public void graficoDefeitoSideTipificacao() {
		
		this.graficoDefeitoSideTipificacao = new PieChartModel();
		
		List<Tipificacao> listaDeTipificacao = this.tipificacaoServico.listarTipificacao();
		
		for (Tipificacao tipificacao : listaDeTipificacao) {			
			
			Integer total = this.defeitoSideServico.listarDefeitoSideTipificacao(tipificacao).size();
			
			this.graficoDefeitoSideTipificacao.set(tipificacao.getNome(), total);
			
		}
		
		this.graficoDefeitoSideTipificacao.setSeriesColors("003245, 004356, 005466, 006476, 007486, 008597, 0095A7, 005B7, 0086C7, 00C6D7");
		this.graficoDefeitoSideTipificacao.setTitle("Tipificação Pró-Ativo");
		this.graficoDefeitoSideTipificacao.setLegendPosition("sw");
		this.graficoDefeitoSideTipificacao.setShowDataLabels(true);
		
	}
	
	public void listarDefeitoSideEncerrado() throws Exception {
		
		Calendar cal = Calendar.getInstance();

		if (this.dataInicio == null) {

			Date date = new Date();

			cal.setTime(date);
			cal.add(Calendar.DATE, 1);
			cal.set(Calendar.HOUR_OF_DAY, -24);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			this.dataInicio = cal.getTime();

		}

		if (this.dataFim == null) {

			this.dataFim = this.dataInicio;

			cal.setTime(this.dataFim);
			cal.add(Calendar.DATE, 1);

			this.dataFim = cal.getTime();

		}else{

			cal.setTime(this.dataFim);
			cal.add(Calendar.DATE, 1);

			this.dataFim = cal.getTime();

		}		

		StatusDefeito statusDefeito = this.statusDefeitoServico.listarStatusDefeitoEspecifico("Encerrado");

		this.defeitoSides = this.defeitoSideServico.listarDefeitoSideEncerrado(statusDefeito, this.dataInicio, this.dataFim);
		
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<DefeitoSide> getDefeitoSides() {
		return defeitoSides;
	}

	public void setDefeitoSides(List<DefeitoSide> defeitoSides) {
		this.defeitoSides = defeitoSides;
	}

	public PieChartModel getGraficoDefeitoSide() {
		return graficoDefeitoSide;
	}

	public void setGraficoDefeitoSide(PieChartModel graficoDefeitoSide) {
		this.graficoDefeitoSide = graficoDefeitoSide;
	}

	public PieChartModel getGraficoDefeitoSideMotivoEncerramento() {
		return graficoDefeitoSideMotivoEncerramento;
	}

	public void setGraficoDefeitoSideMotivoEncerramento(PieChartModel graficoDefeitoSideMotivoEncerramento) {
		this.graficoDefeitoSideMotivoEncerramento = graficoDefeitoSideMotivoEncerramento;
	}

	public PieChartModel getGraficoDefeitoSideTipificacao() {
		return graficoDefeitoSideTipificacao;
	}

	public void setGraficoDefeitoSideTipificacao(PieChartModel graficoDefeitoSideTipificacao) {
		this.graficoDefeitoSideTipificacao = graficoDefeitoSideTipificacao;
	}	
	
}
