/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.awt.Checkbox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.model.ClienteModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marlon
 */
public class ClienteController implements Initializable {

    @FXML
    private Button btAlterar;

    @FXML
    private Button btExcluir;

    @FXML
    private Button btFechar;

    @FXML
    private Button btInserir;

    @FXML
    private Button btPesquisar;

    @FXML
    private Button btSalvar;

   @FXML
    private TableColumn<?, ?> tableViewIdCliente;

    @FXML
    private TableColumn<?, ?> tableViewNomeCliente;

    @FXML
    private TableColumn<?, ?> tableViewCPF;
    
    @FXML
    private TableColumn<?, ?> tableViewRg;
    
    @FXML
    private TableColumn<?, ?> tableViewGenero;
    
    @FXML
    private TableColumn<?, ?> tableViewCelular;
    
    @FXML
    private TableColumn<?, ?> tableViewStatusCliente;
    
    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtCpf;
    
    @FXML
    private TextField txtRg;
    
   @FXML
    private Checkbox cbGenero;
   
    @FXML
    private TextField txtCelular;
    
    @FXML
    private TextField cbStatus;
    
    @FXML
    private TextField txtPesquisarNomeCliente;

    @FXML
    private TextField txtPesquisarCpf;

    @FXML
    void btAlterarClick(ActionEvent event) {
        TableColumn<?, ?> cli = tableViewIdCliente;
    }

    @FXML
    void btExcluirClick(ActionEvent event) {
        
    }

    @FXML
    void btInserirClick(ActionEvent event) {
                ClienteModel cli = new ClienteModel();
        boolean okClicked = showDialog(cli);
    
    }

    @FXML
    void btPesquisarClick(ActionEvent event) {
    }

    @FXML
    void btSalvarClick(ActionEvent event) {
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private boolean showDialog(ClienteModel cli) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
