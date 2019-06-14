package javafx.mvc.events;

import javafx.mvc.model.PedidoModel;

/**
 *
 * @author lukas
 */

public class EventoPedidoFinalizado extends EventoOcorrido {
    
    public EventoPedidoFinalizado(PedidoModel pedido) {
        setNome("PEDIDO_FINALIZADO");
        setDados(pedido);
    }
    
}
