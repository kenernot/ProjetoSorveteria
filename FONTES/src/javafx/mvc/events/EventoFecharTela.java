package javafx.mvc.events;

/**
 *
 * @author lukas
 */

public class EventoFecharTela extends EventoOcorrido {
    
    public EventoFecharTela(String nomeDaTela) {
        setNome("FECHAR_TELA");
        setDados(nomeDaTela);
    }
    
}
