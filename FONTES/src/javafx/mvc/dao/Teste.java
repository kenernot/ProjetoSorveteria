/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kenernot
 */
public class Teste {

    private Connection conn;

    public Teste(Connection conn) {
        this.conn = conn;
    }

    public Boolean testar() throws Exception {
        Boolean resultado = false;
        StringBuilder sql = new StringBuilder();
        sql.append("select 'a';");
        System.out.println(sql);
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql.toString());
            ResultSet rs = ps.executeQuery();
            do {
                resultado = true;
            } while (rs.next());
            System.out.println("ps: ok");
        } catch (SQLException ex) {
            resultado = false;
        }
        System.out.println("resul: " + resultado);
        return resultado;
    }
}
