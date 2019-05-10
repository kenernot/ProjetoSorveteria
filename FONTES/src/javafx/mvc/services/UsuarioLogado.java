package javafx.mvc.services;

import javafx.mvc.model.UsuarioModel;

/**
 *
 * @author Pedro Enju
 */
public class UsuarioLogado {

    private UsuarioModel user;
    private static UsuarioLogado instance = null;

    private UsuarioLogado() {
    }

    public UsuarioModel getUser() {
        return user;
    }

    public void setUser(UsuarioModel user) {
        this.user = user;
    }

    public static UsuarioLogado getInstance() {
        if (UsuarioLogado.instance == null) {
            UsuarioLogado.instance = new UsuarioLogado();
        }
        return UsuarioLogado.instance;
    }
}
