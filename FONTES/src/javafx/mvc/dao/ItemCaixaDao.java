package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.mvc.model.ItemCaixaModel;

/**
 *
 * @author Pedro Enju
 */
public class ItemCaixaDao implements InterfaceDAO {

    private Connection conn;

    public ItemCaixaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void salvar(Object o) throws SQLException {
        if (!(o instanceof ItemCaixaModel)) {
            return;
        }
        ItemCaixaModel model = (ItemCaixaModel) o;
        String sql;

        if (model.getIdItemCaixa()> 0) {
            sql = "update itemcaixa set idCaixa = ?, idPedido = ?, descricao = ?, tipoMov = ?, valor = ? where idItemCaixa = ?";
        } else {
            sql = "insert into itemcaixa (idCaixa, idPedido, descricao, tipoMov, valor, idItemCaixa) values (?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, model.getIdCaixa());
            ps.setInt(2, model.getIdPedido());
            ps.setString(3, model.getDescricao());
            ps.setString(4, model.getTipoMov());
            ps.setDouble(5, model.getValor());
            ps.setInt(6, model.getIdItemCaixa());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        if (!(o instanceof ItemCaixaModel)) {
            return;
        }
        ItemCaixaModel model = (ItemCaixaModel) o;
        String sql = "delete from itemcaixa where idItemCaixa = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setLong(1, model.getIdItemCaixa());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ArrayList<ItemCaixaModel> buscar(String w) throws SQLException {
        String sql = "select * from itemcaixa";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<ItemCaixaModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ItemCaixaModel model = new ItemCaixaModel();
                model.setIdItemCaixa(rs.getInt("idItemCaixa"));
                model.setIdCaixa(rs.getInt("idCaixa"));
                model.setIdPedido(rs.getInt("idPedido"));
                model.setDescricao(rs.getString("descricao"));
                model.setTipoMov(rs.getString("tipoMov"));
                model.setValor(rs.getDouble("valor"));
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