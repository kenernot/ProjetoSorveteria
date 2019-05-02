package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.mvc.model.CaixaModel;

/**
 *
 * @author Pedro Enju
 */
public class CaixaDao implements InterfaceDAO {

    private Connection conn;

    public CaixaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void salvar(Object o) throws SQLException {
        if (!(o instanceof CaixaModel)) {
            return;
        }
        CaixaModel model = (CaixaModel) o;
        String sql;

        if (model.getIdCaixa()> 0) {
            sql = "update caixa set idUsuario = ?, dataAbertura = ?, valorAbertura = ?, valorFinal = ?, dataFechamento = ?, formaRecebimento = ?, valor = ?, valorCliente = ?, valorTroco = ? where idCaixa = ?";
        } else {
            sql = "insert into caixa (idUsuario, dataAbertura, valorAbertura, valorFinal, dataFechamento, formaRecebimento, valor, valorCliente, valorTroco, idCaixa) values (?,?,?,?,?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, model.getIdUsuario());
            ps.setString(2, model.getDataAbertura());
            ps.setDouble(3, model.getValorAbertura());
            ps.setDouble(4, model.getValorFinal());
            ps.setString(5, model.getDataFechamento());
            ps.setString(6, model.getFormaRecebimento());
            ps.setDouble(7, model.getValor());
            ps.setDouble(8, model.getValorCliente());
            ps.setDouble(9, model.getValorTroco());
            ps.setInt(10, model.getIdCaixa());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        if (!(o instanceof CaixaModel)) {
            return;
        }
        CaixaModel model = (CaixaModel) o;
        String sql = "delete from caixa where idCaixa = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setLong(1, model.getIdCaixa());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ArrayList<?> buscar(String w) throws SQLException {
        String sql = "select * from caixa ";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<CaixaModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                while (rs.next()) {
                    CaixaModel model = new CaixaModel();
                    model.setIdCaixa(rs.getInt("idCaixa"));
                    model.setIdUsuario(rs.getInt("idUsuario"));
                    model.setDataAbertura(rs.getDate("dataAbertura").toString());
                    model.setValorAbertura(rs.getDouble("valorAbertura"));
                    model.setValorFinal(rs.getDouble("valorFinal"));
                    model.setDataFechamento(rs.getDate("dataFechamento").toString());
                    model.setFormaRecebimento(rs.getString("formaRecebimento"));
                    model.setValor(rs.getDouble("valor"));
                    model.setValorCliente(rs.getDouble("valorCliente"));
                    model.setValorTroco(rs.getDouble("valorCliente"));
                    al.add(model);
                }
            }
            rs.close();
            ps.close();
            return al;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
}