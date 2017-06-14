package entidades.sistema;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entidades.UsuarioEfika;

@Entity
@Table(name="ufc_defeito_2")
public class Defeito {

	@Id
	private String ss;

	private String instancia;
	
	private Date dataAbertura;
	
	private Date dataVencimento;
	
	private Date dataIntegrado;
	
	private Date dataEncerramento;
	
	private Date dataAcao;
	
	private Boolean encerradoDQTT = false;
	
	private String resultadoFulltest;
	
	@ManyToOne
	private TipoDefeito tipoDefeito;
	
	@ManyToOne
	private Tipificacao tipificacao;
	
	@ManyToOne
	private UsuarioEfika usuarioEfika;
	
	@ManyToOne
	private MotivoEncerramentoDefeito motivoEncerramentoDefeito;
	
	@ManyToOne
	private StatusDefeito statusDefeito;

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

	public Date getDataIntegrado() {
		return dataIntegrado;
	}

	public void setDataIntegrado(Date dataIntegrado) {
		this.dataIntegrado = dataIntegrado;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Date getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(Date dataAcao) {
		this.dataAcao = dataAcao;
	}

	public Boolean getEncerradoDQTT() {
		return encerradoDQTT;
	}

	public void setEncerradoDQTT(Boolean encerradoDQTT) {
		this.encerradoDQTT = encerradoDQTT;
	}

	public String getResultadoFulltest() {
		return resultadoFulltest;
	}

	public void setResultadoFulltest(String resultadoFulltest) {
		this.resultadoFulltest = resultadoFulltest;
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

	public UsuarioEfika getUsuarioEfika() {
		return usuarioEfika;
	}

	public void setUsuarioEfika(UsuarioEfika usuarioEfika) {
		this.usuarioEfika = usuarioEfika;
	}
	
	public MotivoEncerramentoDefeito getMotivoEncerramentoDefeito() {
		return motivoEncerramentoDefeito;
	}

	public void setMotivoEncerramentoDefeito(MotivoEncerramentoDefeito motivoEncerramentoDefeito) {
		this.motivoEncerramentoDefeito = motivoEncerramentoDefeito;
	}

	public StatusDefeito getStatusDefeito() {
		return statusDefeito;
	}

	public void setStatusDefeito(StatusDefeito statusDefeito) {
		this.statusDefeito = statusDefeito;
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
		Defeito other = (Defeito) obj;
		if (ss == null) {
			if (other.ss != null)
				return false;
		} else if (!ss.equals(other.ss))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Defeito [ss=" + ss + "]";
	}	
	
}
