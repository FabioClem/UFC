package entidades.sistema;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import util.JSFUtil;

@Entity
@Table(name="ufc_comentario_defeito")
public class ComentarioDefeito {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Lob
	private String descricao;

	private Date dataComentario;
	
	@ManyToOne
	private Defeito defeito;
	
	@ManyToOne
	private DefeitoSide defeitoSide;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataComentario() {
		return dataComentario;
	}
	
	public String getDataComentarioFormatada() {
		
		return JSFUtil.formatarData(this.dataComentario);
		
	}

	public void setDataComentario(Date dataComentario) {
		this.dataComentario = dataComentario;
	}

	public Defeito getDefeito() {
		return defeito;
	}

	public void setDefeito(Defeito defeito) {
		this.defeito = defeito;
	}	

	public DefeitoSide getDefeitoSide() {
		return defeitoSide;
	}

	public void setDefeitoSide(DefeitoSide defeitoSide) {
		this.defeitoSide = defeitoSide;
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
		ComentarioDefeito other = (ComentarioDefeito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComentarioDefeito [id=" + id + "]";
	}	
	
}
