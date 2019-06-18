package javafx.mvc.model;

/**
 *
 * @author Pedro Enju
 */
public class ItemCaixaModel {

    private int idItemCaixa;
    private int idCaixa;
    private int idPedido;
    private String descricao;
    private String tipoMov;
    private double valor;

    public int getIdItemCaixa() {
        return idItemCaixa;
    }

    public void setIdItemCaixa(int idItemCaixa) {
        this.idItemCaixa = idItemCaixa;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
