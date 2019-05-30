package javafx.mvc.events;

/**
 *
 * @author lukas
 */

public class EventoTrocarTela extends EventoOcorrido {
    
    public EventoTrocarTela(String nomeDaTela) {
        setNome("TROCA_TELA");
        setDados(nomeDaTela);
    }
    
}
