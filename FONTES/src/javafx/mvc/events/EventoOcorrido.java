package javafx.mvc.events;

/**
 *
 * @author lukas
 */
public class EventoOcorrido {
    private String nome;
    private Object source;
    private Object dados;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }
}
