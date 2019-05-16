package javafx.mvc.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Enju
 */
public class Conexao {

    private Connection conn;
    private static Conexao instance = null;

    private Conexao() {
        this.conn = Conexao.createConnection();
    }

    private static Connection createConnection() {
        Properties config = getConfig();
        
        Connection conn = null;
        try {
            Class.forName(config.getProperty("conndriver"));

            conn = DriverManager.getConnection(
                    config.getProperty("connurl"),
                    config
            );

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return conn;
    }

    public static Conexao getInstance() {
        if (Conexao.instance == null) {
            Conexao.instance = new Conexao();
        }
        return Conexao.instance;
    }

    public Connection getConn() {
        return conn;
    }
    
    public static Properties getConfig() {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream(System.getProperty("user.dir").concat("/db.properties")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
       return config; 
    }
}
