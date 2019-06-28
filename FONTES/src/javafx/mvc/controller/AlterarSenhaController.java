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
import javafx.mvc.Main;
import javafx.mvc.contracts.Ouvinte;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.Conexao;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class AlterarSenhaController implements Ouvinte {

    
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
        if (this.altera(Senha)) {
            
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Alterado com Sucesso!");
        alert.show(); 
        this.dialogStage.close();
        
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Não foi possivel alterar a senha!");
            alert.show();
        }
        return false;
    }
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private UsuarioModel um; 
    
    private Stage dialogStage;

    @FXML
    void initialize() {
        Main.subscribe(this);
    }
    
    private boolean altera (String Senha){
        boolean alterado = false;
        String sql;
        try {
            
            Connection conn = Conexao.getInstance().getConn();
            sql = "UPDATE usuario SET senha =? WHERE idUsuario=?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, Senha);
            ps.setInt(2, this.um.getIdUsuario());
            ps.executeUpdate();
            return true; 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return alterado;
        }
      
    }

    @Override
    public void avisandoAqui(EventoOcorrido evento) {
        if (evento.getNome().equals("AlterarSenha")) {
            this.um = (UsuarioModel)evento.getDados(); 
        }
    }

   
   
}
