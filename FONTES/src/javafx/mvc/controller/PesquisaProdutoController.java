/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.mvc.dao.ProdutoDao;
import javafx.mvc.model.ProdutoModel;
import javafx.mvc.services.Conexao;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ngnic
 */
public class PesquisaProdutoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnFiltrar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TableView<ProdutoModel> tableViewProduto;

    @FXML
    private TableColumn<?, ?> tableViewProdutoNome;

    @FXML
    private TableColumn<?, ?> tableViewProdutoValorVenda;

    @FXML
    private TextField txtPesquisa;
    private ProdutoModel produto;
    
    private boolean okClicked;

    public boolean isOkClicked() {
        return okClicked;
    }

    

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    @FXML
    void btnFiltrarClick(ActionEvent event) {
        this.okClicked = true;
        this.dialogStage.close();
    }

    @FXML
    void btnPesquisarClick(ActionEvent event) {
        try {
            listarUsuarios();
        } catch (Exception ex) {
            System.out.println("AOISJDOIASODASD");
        }
    }

    private ProdutoDao pd;
    private ObservableList<ProdutoModel> listaObserver;
    private Stage dialogStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.pd = new ProdutoDao(Conexao.getInstance().getConn());

        tableViewProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> selecionaProduto(newvalue));

    }

    private void listarUsuarios() throws Exception {

        tableViewProdutoNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        tableViewProdutoValorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));

        String sql = " 1=1 ";
        if (txtPesquisa.getText().length() > 0) {
            if (txtPesquisa.getText().matches("^[0-9]*$")) {
                sql += " OR idProduto = " + txtPesquisa.getText();
            }

            if (txtPesquisa.getText().length() == 1) {
                sql += " OR tipoProduto = '" + txtPesquisa.getText() + "' ";
            }

            sql += " OR nomeProduto like '%" + txtPesquisa.getText() + "%' ";
            sql += " order by nomeProduto ASC limit 100;";
        }
        
        System.out.println(sql);

        List<ProdutoModel> lista = pd.buscar(sql);
        listaObserver = FXCollections.observableArrayList(lista);

        tableViewProduto.setItems(listaObserver);
    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
    
    

    private void selecionaProduto(ProdutoModel c) {
        if (c == null) {
            this.produto = null;
        } else {
            this.produto = c;
        }
    }

}
