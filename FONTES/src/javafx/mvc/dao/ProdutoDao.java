package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.mvc.model.ProdutoModel;

/**
 *
 * @author Pedro Enju
 */
public class ProdutoDao implements InterfaceDAO {

    private Connection conn;

    public ProdutoDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void salvar(Object o) throws SQLException {
        if (!(o instanceof ProdutoModel)) {
            return;
        }
        ProdutoModel model = (ProdutoModel) o;
        String sql;

        if (model.getIdProduto() > 0) {
            sql = "update produto set nomeProduto = ?, valorCompra = ?, valorVenda = ?, observacao = ?, status = ?, tipoProduto = ? where idProduto = ?";
        } else {
            sql = "insert into produto (nomeProduto,valorCompra,valorVenda,observacao,status,tipoProduto,idProduto) values (?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, model.getNomeProduto());
            ps.setDouble(2, model.getValorCompra());
            ps.setDouble(3, model.getValorVenda());
            ps.setString(4, model.getObservacao());
            ps.setString(5, model.getStatus());
            ps.setString(6, model.getTipoProduto());
            ps.setInt(7, model.getIdProduto());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        if (!(o instanceof ProdutoModel)) {
            return;
        }
        ProdutoModel model = (ProdutoModel) o;
        String sql = "delete from produto where idProduto = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setLong(1, model.getIdProduto());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ArrayList<ProdutoModel> buscar(String w) throws SQLException {
        String sql = "select * from produto";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<ProdutoModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ProdutoModel model = new ProdutoModel();
                model.setIdProduto(rs.getInt("idProduto"));
                model.setNomeProduto(rs.getString("nomeProduto"));
                model.setValorCompra(rs.getDouble("valorCompra"));
                model.setValorVenda(rs.getDouble("valorVenda"));
                model.setObservacao(rs.getString("observacao"));
                model.setStatus(rs.getString("status"));
                model.setTipoProduto(rs.getString("tipoProduto"));
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