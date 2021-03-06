package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import javafx.mvc.model.ItemPedidoModel;
import javafx.mvc.model.PedidoModel;
import javafx.mvc.services.Conexao;

/**
 *
 * @author Pedro Enju
 */
public class PedidoDao implements InterfaceDAO {

    private Connection conn;

    public PedidoDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public PedidoModel salvar(Object o) throws SQLException {
        if (!(o instanceof PedidoModel)) {
            return new PedidoModel();
        }
        PedidoModel model = (PedidoModel) o;
        String sql;

        if (model.getIdPedido() > 0) {
            sql = "update pedido set idCliente = ?, idUsuario = ?, dataPedido = ?, descricaoPedido = ?, valorTotal = ?, valorDesconto = ?, valorPagar = ?, qtdTotal = ?, status = ? where idPedido = ?";
        } else {
            sql = "insert into pedido (idCliente, idUsuario, dataPedido, descricaoPedido, valorTotal, valorDesconto, valorPagar, qtdTotal, idPedido, status) values (?,?,?,?,?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, model.getIdCliente());
            ps.setInt(2, model.getIdUsuario());
            ps.setString(3, model.getDataPedido());
            ps.setString(4, model.getDescricaoPedido());
            ps.setDouble(5, model.getValorTotal());
            ps.setDouble(6, model.getValorDesconto());
            ps.setDouble(7, model.getValorPagar());
            ps.setInt(8, model.getQtdTotal());
            ps.setInt(9, model.getIdPedido());
            ps.setString(10, model.getStatus());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                model.setIdPedido(rs.getInt(1));
            }

            rs.close();
            ps.close();

            ItemPedidoDao itemDao = new ItemPedidoDao(Conexao.getInstance().getConn());

            ArrayList<ItemPedidoModel> listaItemPedido = model.getListaItemPedido();

            for (ItemPedidoModel item : listaItemPedido) {
                item.setIdPedido(model.getIdPedido());
                itemDao.salvar(item);
            }
            
            ArrayList<ItemPedidoModel> itemsPedidosBanco = itemDao.buscar("idPedido = " + model.getIdPedido());

            itemsPedidosBanco.removeAll(listaItemPedido);
            
            for (ItemPedidoModel item : itemsPedidosBanco) {
                itemDao.excluir(item);
            }
            
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return model;
    }

    @Override
    public void excluir(Object o) throws SQLException {
        if (!(o instanceof PedidoModel)) {
            return;
        }
        PedidoModel model = (PedidoModel) o;
        String sql = "delete from pedido where idPedido = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setLong(1, model.getIdPedido());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ArrayList<PedidoModel> buscar(String w) throws SQLException {
        String sql = "select * from pedido";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<PedidoModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PedidoModel model = new PedidoModel();
                model.setIdPedido(rs.getInt("idPedido"));
                model.setIdCliente(rs.getInt("idCliente"));
                model.setIdUsuario(rs.getInt("idUsuario"));
                model.setDataPedido(rs.getDate("dataPedido").toString());
                model.setDescricaoPedido(rs.getString("descricaoPedido"));
                model.setValorTotal(rs.getDouble("valorTotal"));
                model.setValorDesconto(rs.getDouble("valorDesconto"));
                model.setValorPagar(rs.getDouble("valorPagar"));
                model.setQtdTotal(rs.getInt("qtdTotal"));
                model.setStatus(rs.getString("status"));
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
