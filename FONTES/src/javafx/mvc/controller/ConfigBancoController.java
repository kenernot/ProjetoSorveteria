/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author drink
 */
public class ConfigBancoController implements Initializable {

    @FXML
    private Font x1;
    @FXML
    private TextField edSenha;
    @FXML
    private TextField edUsuario;
    @FXML
    private TextField edDB;
    @FXML
    private Button btnContinuar;

    private boolean configurado = false;

    private Stage dialogStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnContinuaClick(ActionEvent event) {
        
        if (edUsuario.getText().isEmpty()) {
            mostraErro("O usuário é Obrigatório!");
            return;
        }
        if (edDB.getText().isEmpty()) {
            mostraErro("O Banco é Obrigatório!");
            return;
        }
        
        
        Properties prop = new Properties();
        prop.put("user", edUsuario.getText());
        prop.put("password", edSenha.getText());
        prop.put("connurl", "jdbc:mysql://localhost:3306/" + edDB.getText());
        prop.put("conndriver", "com.mysql.jdbc.Driver");
        try {
            OutputStream fos = new FileOutputStream(System.getProperty("user.dir").concat("/db.properties"));
            prop.store(fos, "My Awesome Config");
            fos.close();
            configurado = true;
            dialogStage.close();
        } catch (FileNotFoundException ex) {
            mostraErro(ex.getMessage());
        } catch (IOException ex) {
            mostraErro(ex.getMessage());
        }
    }

    public void mostraErro(String message) {
        Alert erro = new Alert(Alert.AlertType.ERROR);
        erro.setContentText(message);
        erro.show();
    }

    public boolean isConfigurado() {
        return configurado;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
