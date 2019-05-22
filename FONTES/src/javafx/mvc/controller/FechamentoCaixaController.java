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
public class FechamentoCaixaController implements Initializable {

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_salvar;

    @FXML
    private TextField edt_entradas;

    @FXML
    private TextField edt_saidas;

    @FXML
    private TextField edt_saldoFinal;
    
     @FXML
    void btnSalvarClick(ActionEvent event) {
        this.caixa.set 
   }
    
     @FXML
    void btnCancelarClick(ActionEvent event) {
    }

   private CaixaModel caixa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
