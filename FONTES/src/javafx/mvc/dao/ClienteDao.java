package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.mvc.model.ClienteModel;

/**
 *
 * @author Pedro Enju
 */
public class ClienteDao implements InterfaceDAO {

    private Connection conn;

    public ClienteDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void salvar(Object o) throws SQLException {
        if (!(o instanceof ClienteModel)) {
            return;
        }
        ClienteModel model = (ClienteModel) o;
        String sql;
        if (model.getIdCliente() > 0) {
            sql = "update cliente set nomeCliente = ?, cpf = ?, rg = ?, genero = ?, celular = ?, status = ? where idCliente = ?";
        } else {
            sql = "insert into cliente (nomeCliente,cpf,rg,genero,celular,status,idCliente) values (?,?,?,?,?,?,?)";
        }
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, model.getNomeCliente());
            ps.setString(2, model.getCpf());
            ps.setString(3, model.getRg());
            ps.setString(4, model.getGenero());
            ps.setString(5, model.getCelular());
            ps.setString(6, model.getStatus());
            ps.setInt(7, model.getIdCliente());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void excluir(Object o) throws SQLException {
        if (!(o instanceof ClienteModel)) {
            return;
        }
        ClienteModel model = (ClienteModel) o;
        String sql = "delete from cliente where idCliente = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setLong(1, model.getIdCliente());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ArrayList<ClienteModel> buscar(String w) throws SQLException {
        String sql = "select * from cliente";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<ClienteModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
                
            while (rs.next()) {
                ClienteModel model = new ClienteModel();
                model.setIdCliente(rs.getInt("idCliente"));
                model.setNomeCliente(rs.getString("nomeCliente"));
                model.setCpf(rs.getString("cpf"));
                model.setRg(rs.getString("rg"));
                model.setGenero(rs.getString("genero"));
                model.setCelular(rs.getString("celular"));
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