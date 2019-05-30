package javafx.mvc.model;

/**
 *
 * @author Pedro Enju
 */
public class CaixaModel {

    private int idCaixa;
    private int idUsuario;
    private String dataAbertura;
    private double valorAbertura;
    private double valorFinal;
    private double informarSaldo;
    private String dataFechamento;

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public double getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(double valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public double getInformarSaldo() {
        return informarSaldo;
    }

    public void setInformarSaldo(double informarSaldo) {
        this.informarSaldo = informarSaldo;
    }

    public String getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(String dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

}