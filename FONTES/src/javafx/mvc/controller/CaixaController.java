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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Marlon
 */
public class CaixaController implements Initializable {

    @FXML
    private TextField TxtValorCliente;

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
    private Button btnGerarRelatorio;

    @FXML
    private Button btSalvar;

    @FXML
    private ComboBox<?> comboUsuario;

    @FXML
    private TableColumn<?, ?> tableViewDtAbertura;

    @FXML
    private TableColumn<?, ?> tableViewDtFechamento;

    @FXML
    private TableColumn<?, ?> tableViewId;

    @FXML
    private TableColumn<?, ?> tableViewUsuario;

    @FXML
    private TableColumn<?, ?> tableViewValor;

    @FXML
    private TextField txtDtAbertura;

    @FXML
    private TextField txtDtFechamento;

    @FXML
    private TextField txtFormaRec;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPesquisaDataAbertura;

    @FXML
    private TextField txtPesquisaDataFechamento;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtValorTroco;

    @FXML
    private TextField txtVlAbertura;

    @FXML
    private TextField txtVlFinal;


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

}
