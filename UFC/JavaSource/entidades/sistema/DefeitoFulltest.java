package entidades.sistema;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ufc_defeito_fulltest")
public class DefeitoFulltest {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String ss;
	
	private String instancia;
	
	private Date dataAbertura;
	
	private Date dataVencimento;
	
	private String fulltest;
	
	private String informacoes;
	
	@ManyToOne
	private Tipificacao tipificacao;
	
	@ManyToOne
	private Lote lote;
	
	@ManyToOne
	private StatusFulltest statusFulltest;
	
	@ManyToOne
	private TipoDefeito tipoDefeito;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String getInstancia() {
		return instancia;
	}

	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}

	public Date getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getFulltest() {
		return fulltest;
	}

	public void setFulltest(String fulltest) {
		this.fulltest = fulltest;
	}

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	public Tipificacao getTipificacao() {
		return tipificacao;
	}

	public void setTipificacao(Tipificacao tipificacao) {
		this.tipificacao = tipificacao;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public StatusFulltest getStatusFulltest() {
		return statusFulltest;
	}

	public void setStatusFulltest(StatusFulltest statusFulltest) {
		this.statusFulltest = statusFulltest;
	}

	public TipoDefeito getTipoDefeito() {
		return tipoDefeito;
	}

	public void setTipoDefeito(TipoDefeito tipoDefeito) {
		this.tipoDefeito = tipoDefeito;
	}	
	
}
