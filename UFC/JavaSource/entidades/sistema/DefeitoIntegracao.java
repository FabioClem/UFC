package entidades.sistema;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ufc_defeito_integracao")
public class DefeitoIntegracao {
	
	@Id
	private String ss;
	
	private String instancia;
	
	private Date dataAbertura;
	
	private Date dataVencimento;
	
	private Boolean fulltest;
	
	@ManyToOne
	private StatusFulltest statusFulltest;
	
	@ManyToOne
	private Lote lote;
	
	@ManyToOne
	private TipoDefeito tipoDefeito;
	
	@ManyToOne
	private Tipificacao tipificacao;

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

	public Boolean getFulltest() {
		return fulltest;
	}

	public void setFulltest(Boolean fulltest) {
		this.fulltest = fulltest;
	}

	public StatusFulltest getStatusFulltest() {
		return statusFulltest;
	}

	public void setStatusFulltest(StatusFulltest statusFulltest) {
		this.statusFulltest = statusFulltest;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public TipoDefeito getTipoDefeito() {
		return tipoDefeito;
	}

	public void setTipoDefeito(TipoDefeito tipoDefeito) {
		this.tipoDefeito = tipoDefeito;
	}

	public Tipificacao getTipificacao() {
		return tipificacao;
	}

	public void setTipificacao(Tipificacao tipificacao) {
		this.tipificacao = tipificacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ss == null) ? 0 : ss.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefeitoIntegracao other = (DefeitoIntegracao) obj;
		if (ss == null) {
			if (other.ss != null)
				return false;
		} else if (!ss.equals(other.ss))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DefeitoIntegracao [ss=" + ss + "]";
	}	
	
}
