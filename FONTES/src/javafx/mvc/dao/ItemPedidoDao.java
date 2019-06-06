package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.mvc.model.ItemPedidoModel;

/**
 *
 * @author Pedro Enju
 */
public class ItemPedidoDao implements InterfaceDAO {

    private Connection conn;

    public ItemPedidoDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public ItemPedidoModel salvar(Object o) throws SQLException {
        if (!(o instanceof ItemPedidoModel)) {
            return new ItemPedidoModel();
        }
        ItemPedidoModel model = (ItemPedidoModel) o;
        String sql;

        if (model.getIdItemPedido() > 0) {
            sql = "update itempedido set idPedido = ?, idProduto = ?, qtd = ?, valorUnitario = ?, valorDesconto = ?, valorTotal = ? where idItemPedido = ?";
        } else {
            sql = "insert into itempedido (idPedido, idProduto, qtd, valorUnitario, valorDesconto, valorTotal, idItemPedido) values (?,?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, model.getIdPedido());
            ps.setInt(2, model.getIdProduto());
            ps.setDouble(3, model.getQtd());
            ps.setDouble(4, model.getValorUnitario());
            ps.setDouble(5, model.getValorDesconto());
            ps.setDouble(6, model.getValorTotal());
            ps.setInt(7, model.getIdItemPedido());
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                model.setIdItemPedido(rs.getInt(1));
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return model;
    }

    @Override
    public void excluir(Object o) throws SQLException {
        if (!(o instanceof ItemPedidoModel)) {
            return;
        }
        ItemPedidoModel model = (ItemPedidoModel) o;
        String sql = "delete from itempedido where idItemPedido = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setLong(1, model.getIdItemPedido());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ArrayList<ItemPedidoModel> buscar(String w) throws SQLException {
        String sql = "select * from itempedido";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<ItemPedidoModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ItemPedidoModel model = new ItemPedidoModel();
                model.setIdItemPedido(rs.getInt("idItemPedido"));
                model.setIdPedido(rs.getInt("idPedido"));
                model.setIdProduto(rs.getInt("idProduto"));
                model.setQtd(rs.getDouble("qtd"));
                model.setValorUnitario(rs.getDouble("valorUnitario"));
                model.setValorDesconto(rs.getDouble("valorDesconto"));
                model.setValorTotal(rs.getDouble("valorTotal"));
                al.add(model);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return al;
    }

}