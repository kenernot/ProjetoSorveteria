package javafx.mvc.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Pedro Enju
 */
public interface InterfaceDAO {

    public Object salvar(Object o) throws SQLException;

    public void excluir(Object o) throws SQLException;

    public ArrayList<?> buscar(String w) throws SQLException;
}
