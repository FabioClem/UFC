package entidades.sistema;

import entidades.AbstractEntity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entidades.UsuarioEfika;

@Entity
@Table(name = "ufc_acoes_2")
public class Acoes extends AbstractEntity {

    private Date dataAcao;

    @ManyToOne
    private UsuarioEfika usuarioEfika;

    @ManyToOne
    private StatusDefeito statusDefeito;

    @ManyToOne
    private Defeito defeito;

    public Date getDataAcao() {
        return dataAcao;
    }

    public void setDataAcao(Date dataAcao) {
        this.dataAcao = dataAcao;
    }

    public UsuarioEfika getUsuarioEfika() {
        return usuarioEfika;
    }

    public void setUsuarioEfika(UsuarioEfika usuarioEfika) {
        this.usuarioEfika = usuarioEfika;
    }

    public StatusDefeito getStatusDefeito() {
        return statusDefeito;
    }

    public void setStatusDefeito(StatusDefeito statusDefeito) {
        this.statusDefeito = statusDefeito;
    }

    public Defeito getDefeito() {
        return defeito;
    }

    public void setDefeito(Defeito defeito) {
        this.defeito = defeito;
    }

}
