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
import javafx.mvc.model.ProdutoModel;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marlon
 */
public class ProdutoController implements Initializable {

     @FXML
    private ComboBox<String> CbStatus;

    @FXML
    private ComboBox<String> CbTipo;

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
    private TableColumn<?, ?> tableViewId;

    @FXML
    private TableColumn<?, ?> tableViewNome;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPesquisarNome;

    @FXML
    private TextField txtValorCompra;

    @FXML
    private TextField txtValorVenda;

    @FXML
    private TextArea txtObservacao;

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
        this.produto.setNomeProduto(txtNome.getText());
        this.produto.setValorCompra(Double.valueOf(txtValorCompra.getText()));
        this.produto.setValorVenda(Double.valueOf(txtValorVenda.getText()));
        this.produto.setTipoProduto(CbTipo.getValue());
        this.produto.setStatus(CbStatus.getValue());
        this.produto.setObservacao(txtObservacao.getText());
    }
    


    private Stage dialogStage;
    private Boolean okClicked = false;
    private ProdutoModel produto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Boolean getOkClicked() {
        return okClicked;
    }

    public void setOkClicked(Boolean okClicked) {
        this.okClicked = okClicked;
    }

    public void setProduto(ProdutoModel produto) {
        txtNome.setText(produto.getNomeProduto());
        txtValorCompra.setText(Double.toString(produto.getValorCompra()));
        txtValorVenda.setText(Double.toString(produto.getValorVenda()));
        CbTipo.setValue(produto.getTipoProduto());
        CbStatus.setValue(produto.getStatus());
        txtObservacao.setText(produto.getObservacao());
    }

    
}

