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
import javafx.mvc.model.ItemCaixaModel;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class ItemCaixaController implements Initializable {

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_salvar;

    @FXML
    private ComboBox<?> cb_tipoMov;

    @FXML
    private TextField edt_valor;

    @FXML
    private TextArea txt_desc;
    
     @FXML
    void btnSalvarClick(ActionEvent event) {
        this.itemcaixa.setTipoMov((String) cb_tipoMov.getValue());
        this.itemcaixa.setValor(Double.valueOf(edt_valor.getText()));
        this.itemcaixa.setDescricao(txt_desc.getText());
        
        
    }
    
     @FXML
    void btnCancelarClick(ActionEvent event) {
    }

   
    
    private ItemCaixaModel itemcaixa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
