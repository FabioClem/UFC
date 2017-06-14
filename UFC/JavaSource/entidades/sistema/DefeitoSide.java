package entidades.sistema;

import entidades.AbstractEntity;
import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

import entidades.UsuarioEfika;
import util.JSFUtil;

@Entity
@Table(name = "ufc_defeito_side_2")
public class DefeitoSide extends AbstractEntity {

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
    private MotivoEncerramentoDefeitoSide motivoEncerramento;

    private String informacoes;

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

    public MotivoEncerramentoDefeitoSide getMotivoEncerramento() {
        return motivoEncerramento;
    }

    public void setMotivoEncerramento(MotivoEncerramentoDefeitoSide motivoEncerramento) {
        this.motivoEncerramento = motivoEncerramento;
    }

    public String getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

}
