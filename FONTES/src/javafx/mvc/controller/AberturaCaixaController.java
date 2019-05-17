/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.model.CaixaModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class AberturaCaixaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField edt_valorInic;
    
    @FXML
    private Button btn_salvar;
    
      @FXML
    void btnSalvarClick(ActionEvent event) {
        this.caixa.setValorAbertura(Double.valueOf(edt_valorInic.getText()));
    }
    private CaixaModel caixa;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
