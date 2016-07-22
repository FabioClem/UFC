package entidades.sistema;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entidades.UsuarioEfika;
import util.JSFUtil;

@Entity
@Table(name="ufc_defeito_side")
public class DefeitoSide {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String ss;
	
	private String instancia;
	
	private Date dataIntegrado;
	
	private Date dataAcao;
		
	@ManyToOne	
	private Tipificacao tipificacao;
	
	@ManyToOne
	private UsuarioEfika usuarioEfika;
	
	@ManyToOne
	private StatusDefeito statusDefeito;
	
	@ManyToOne
	private Lote lote;
	
	@ManyToOne
	private MotivoEncerramento motivoEncerramento;
	
	private String informacoes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstancia() {
		return instancia;
	}

	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}

	public Date getDataIntegrado() {
		return dataIntegrado;
	}
	
	public String getDataIntegradoFormatado() {
		
		return JSFUtil.formatarDataHra(this.dataIntegrado);
		
	}

	public void setDataIntegrado(Date dataIntegrado) {
		this.dataIntegrado = dataIntegrado;
	}

	public Date getDataAcao() {
		return dataAcao;
	}
	
	public String getDataAcaoFormatado() {
		
		return JSFUtil.formatarDataHra(this.dataAcao);
		
	}

	public void setDataAcao(Date dataAcao) {
		this.dataAcao = dataAcao;
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

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}	

	public StatusDefeito getStatusDefeito() {
		return statusDefeito;
	}

	public void setStatusDefeito(StatusDefeito statusDefeito) {
		this.statusDefeito = statusDefeito;
	}	

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}	

	public MotivoEncerramento getMotivoEncerramento() {
		return motivoEncerramento;
	}

	public void setMotivoEncerramento(MotivoEncerramento motivoEncerramento) {
		this.motivoEncerramento = motivoEncerramento;
	}	

	public String getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(String informacoes) {
		this.informacoes = informacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DefeitoSide other = (DefeitoSide) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DefeitoSide [id=" + id + "]";
	}
	
}
