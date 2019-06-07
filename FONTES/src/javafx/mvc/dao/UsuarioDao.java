package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.mvc.model.UsuarioModel;

/**
 *
 * @author Pedro Enju
 */
public class UsuarioDao implements InterfaceDAO {

    private Connection conn;

    public UsuarioDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public UsuarioModel salvar(Object o) throws SQLException {
        if (!(o instanceof UsuarioModel)) {
            return new UsuarioModel();
        }
        UsuarioModel model = (UsuarioModel) o;
        String sql;

        if (model.getIdUsuario() > 0) {
            sql = "update usuario set nomeFuncionario = ?, nomeUsuario = ?, senhaUsuario = ?, status = ?, cliente = ?, usuario = ?, produto = ? where idUsuario = ?";
        } else {
            sql = "insert into usuario (nomeFuncionario,nomeUsuario,senhaUsuario,status,cliente,usuario,produto,idUsuario) values (?,?,?,?,?,?,?,?)";
        }

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, model.getNomeFuncionario());
            ps.setString(2, model.getNomeUsuario());
            ps.setString(3, model.getSenhaUsuario());
            ps.setString(4, model.getStatus());
            ps.setString(5, model.getCliente());
            ps.setString(6, model.getUsuario());
            ps.setString(7, model.getProduto());
            ps.setInt(8, model.getIdUsuario());
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                model.setIdUsuario(rs.getInt(1));
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
        if (!(o instanceof UsuarioModel)) {
            return;
        }
        UsuarioModel model = (UsuarioModel) o;
        String sql = "delete from usuario where idUsuario = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setLong(1, model.getIdUsuario());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public ArrayList<UsuarioModel> buscar(String w) throws SQLException {
        String sql = "select * from usuario";

        if (!w.isEmpty()) {
            sql += " where " + w;
        }

        ArrayList<UsuarioModel> al = new ArrayList();
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                UsuarioModel model = new UsuarioModel();
                model.setIdUsuario(rs.getInt("idUsuario"));
                model.setNomeFuncionario(rs.getString("nomeFuncionario"));
                model.setNomeUsuario(rs.getString("nomeUsuario"));
                model.setSenhaUsuario(rs.getString("senhaUsuario"));
                model.setStatus(rs.getString("status"));
                model.setCliente(rs.getString("cliente"));
                model.setUsuario(rs.getString("usuario"));
                model.setProduto(rs.getString("produto"));
                al.add(model);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        return al;
    }

    public void remove(UsuarioModel um) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   

   
}