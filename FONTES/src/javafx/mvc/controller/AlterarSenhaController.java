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
import javafx.mvc.events.EventoFecharTela;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.events.EventoTrocarTela;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.Conexao;
import javafx.mvc.services.HashSHA2;
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
        this.tirarTela();
    }

    private void tirarTela(){
        EventoFecharTela ft = new EventoFecharTela("AlterarSenha");
        Main.avisaOuvintes(ft);
        EventoTrocarTela et = new EventoTrocarTela("Usuario"); 
        Main.avisaOuvintes(et);
        
    }
    
    @FXML
    void btnConfirmaClicked(ActionEvent event) {
        if (txtSenha.getText().equals(txtConfirmaSenha.getText())) {
                    
      String senha = HashSHA2.hashSHA2(txtSenha.getText());
      EventoOcorrido ec = new EventoOcorrido(); 
      ec.setNome("senhaAlterada");
      ec.setDados(senha);
      Main.avisaOuvintes(ec);
        this.tirarTela();
        } else {
            
            Alert alerta = new Alert(Alert.AlertType.ERROR); 
            alerta.setContentText("Os dois campos de senha precisam ser iguais!");
            alerta.show();
        }
        
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
    
    private boolean altera (){
        String senha = HashSHA2.hashSHA2(txtSenha.getText()); 
        boolean alterado = false;
        String sql;
        try {
            
            Connection conn = Conexao.getInstance().getConn();
            sql = "UPDATE usuario SET senhaUsuario = ? WHERE idUsuario=?";
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ps.setString(1, senha);
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
