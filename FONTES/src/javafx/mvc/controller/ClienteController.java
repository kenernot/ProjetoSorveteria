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
import javafx.mvc.model.ClienteModel;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Marlon
 */
public class ClienteController implements Initializable {

 
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private ComboBox<?> cbGenero;

    @FXML
    private ComboBox<?> cbStatus;

    @FXML
    private TableColumn<?, ?> tableViewCPF;

    @FXML
    private TableColumn<?, ?> tableViewCelular;

    @FXML
    private TableView<?> tableViewCliente;

    @FXML
    private TableColumn<?, ?> tableViewGenero;

    @FXML
    private TableColumn<?, ?> tableViewIdCliente;

    @FXML
    private TableColumn<?, ?> tableViewNomeCliente;

    @FXML
    private TableColumn<?, ?> tableViewRg;

    @FXML
    private TableColumn<?, ?> tableViewStatusCliente;

    @FXML
    private TextField txtCelular;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtPesquisarCpf;

    @FXML
    private TextField txtPesquisarNomeCliente;

    @FXML
    private TextField txtRg;


    @FXML
    void btAlterarClick(ActionEvent event) {
    }

    @FXML
    void btExcluirClick(ActionEvent event) {
    }

    @FXML
    void btInserirClick(ActionEvent event) {
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

    private void listarUsuario() throws  Exception{
        tableViewIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        tableViewNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableViewCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableViewRg.setCellValueFactory(new PropertyValueFactory<>("RG"));
        tableViewGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tableViewCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tableViewStatusCliente.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        String criterios;
        
        if(!txtPesquisarNomeCliente.getText().trim().isEmpty()){
        
            String par = txtPesquisarNomeCliente.getText();
            if(!par.matches("[^´~%';-_\\/\\*]+")) {
            
        }
    }
        

}

}