package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ufc_controle_usuario")
public class ControleUsuario {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Boolean ativo = false;
	
	private String tipoDefeito;
	
	private Boolean adm = false;
	
	@ManyToOne
	private UsuarioEfika usuarioEfika;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getTipoDefeito() {
		return tipoDefeito;
	}

	public void setTipoDefeito(String tipoDefeito) {
		this.tipoDefeito = tipoDefeito;
	}

	public UsuarioEfika getUsuarioEfika() {
		return usuarioEfika;
	}

	public void setUsuarioEfika(UsuarioEfika usuarioEfika) {
		this.usuarioEfika = usuarioEfika;
	}	

	public Boolean getAdm() {
		return adm;
	}

	public void setAdm(Boolean adm) {
		this.adm = adm;
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
		ControleUsuario other = (ControleUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ControleUsuario [id=" + id + "]";
	}
	
}
