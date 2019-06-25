package javafx.mvc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.Conexao;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class AlterarSenhaController {

    
    @FXML
    private Button btnCancela;

    @FXML
    private Button btnConfirmaSenha;

    @FXML
    private TextField txtConfirmaSenha;

    @FXML
    private TextField txtSenha;
    
    private String Senha;
    
     public boolean isOkClicked() {
        return okClicked;
    }

    private UsuarioModel usuario;
    private boolean okClicked = false;

    @FXML
    void btnCancelaClicked(ActionEvent event) {
        this.dialogStage.close();
    }

    @FXML
    boolean btnConfirmaClicked(ActionEvent event) {
        this.altera(Senha);
        return false;
    }
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    
    private Stage dialogStage;

    @FXML
    void initialize() {
       
    }
    
    private boolean altera (String Senha){
        boolean alterado = false;
        String sql;
        try {
            
            Connection conn = Conexao.getInstance().getConn();
            sql = "UPDATE usuario SET senha =? WHERE senha=";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, Senha);
            ResultSet rs;
            rs = ps.executeQuery();
            if (rs.next()) {
                
                Senha = rs.getString("senha");
                alterado=true; 
               
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel alterar a senha!");
                ps.close();
                return alterado; 
               
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return alterado;
        }
        
        return isOkClicked();

}

   
   
}
