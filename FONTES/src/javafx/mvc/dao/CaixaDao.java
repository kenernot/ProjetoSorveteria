package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public CaixaModel salvar(Object o) throws SQLException {
        if (!(o instanceof CaixaModel)) {
            return new CaixaModel();
        }
        CaixaModel model = (CaixaModel) o;
        String sql;

        if (model.getIdCaixa() > 0) {
            sql = "update caixa set idUsuario = ?, valorAbertura = ?, valorFinal = ?, informarSaldo = ?, dataFechamento = ? where idCaixa = ?";
        } else {
            sql = "insert into caixa (idUsuario, dataAbertura, valorAbertura, valorFinal, informarSaldo, dataFechamento, idCaixa) values (?,NOW(),?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, model.getIdUsuario());
            ps.setDouble(2, model.getValorAbertura());
            ps.setDouble(3, model.getValorFinal());
            ps.setDouble(4, model.getInformarSaldo());
            ps.setString(5, model.getDataFechamento());
            ps.setInt(6, model.getIdCaixa());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                model.setIdCaixa(rs.getInt(1));
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
    public ArrayList<CaixaModel> buscar(String w) throws SQLException {
        String sql = "select * from caixa";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<CaixaModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CaixaModel model = new CaixaModel();
                model.setIdCaixa(rs.getInt("idCaixa"));
                model.setIdUsuario(rs.getInt("idUsuario"));
                model.setDataAbertura(rs.getDate("dataAbertura").toString());
                model.setValorAbertura(rs.getDouble("valorAbertura"));
                model.setValorFinal(rs.getDouble("valorFinal"));
                model.setInformarSaldo(rs.getDouble("informarSaldo"));
                model.setDataFechamento(rs.getDate("dataFechamento").toString());
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
