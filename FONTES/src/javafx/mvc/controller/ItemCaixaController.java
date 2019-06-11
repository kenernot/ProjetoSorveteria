/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.dao.ItemCaixaDao;
import javafx.mvc.model.ItemCaixaModel;
import javafx.mvc.services.CaixaAberto;
import javafx.mvc.services.Conexao;
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
    private ComboBox<String> cb_tipoMov;

    @FXML
    private TextField edt_valor;

    @FXML
    private TextArea txt_desc;
    
     @FXML
    void btnSalvarClick(ActionEvent event) {
        this.itemcaixa.setTipoMov((String) cb_tipoMov.getValue().substring(0, 1));
        this.itemcaixa.setValor(Double.valueOf(edt_valor.getText()));
        this.itemcaixa.setDescricao(txt_desc.getText());
        this.itemcaixa.setIdCaixa(CaixaAberto.getInstance().getCaixa().getIdCaixa());
        
        try {
            this.itd.salvar(this.itemcaixa);
        } catch (SQLException ex) {
            Logger.getLogger(ItemCaixaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    void btnCancelarClick(ActionEvent event) {
    }

   
    
    private ItemCaixaModel itemcaixa;
    private ItemCaixaDao itd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.itd = new ItemCaixaDao(Conexao.getInstance().getConn());
        this.itemcaixa = new ItemCaixaModel();
        cb_tipoMov.getItems().clear();
        cb_tipoMov.getItems().add("Entrada");
        cb_tipoMov.getItems().add("Saida");
    }    
    
}
