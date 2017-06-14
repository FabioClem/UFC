package entidades.sistema;

import entidades.AbstractEntity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import util.JSFUtil;

@Entity
@Table(name = "ufc_comentario_defeito_2")
public class ComentarioDefeito extends AbstractEntity {

    @Lob
    private String descricao;

    private Date dataComentario;

    @ManyToOne
    private Defeito defeito;

    @ManyToOne
    private DefeitoSide defeitoSide;

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

}
