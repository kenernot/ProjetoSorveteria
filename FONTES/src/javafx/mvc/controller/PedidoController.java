package javafx.mvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PedidoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> TableViewColumnID;

    @FXML
    private TableColumn<?, ?> TableViewColumnNome;

    @FXML
    private TableColumn<?, ?> TableViewColumnQtd;

    @FXML
    private TableColumn<?, ?> TableViewColumnValor;

    @FXML
    private TableColumn<?, ?> TableViewColumnValorTotal;

    @FXML
    private Button btCancelarPedido;

    @FXML
    private Button btAdicionarItemPedido;
    
    @FXML
    private Button btDeletarItemPedido;
    
    @FXML
    private Button btFinalizarPedido;

    @FXML
    private Button btNovoPedido;

    @FXML
    private Button btPesquisarPedido;

    @FXML
    private ComboBox<?> comboPedido;

    @FXML
    private TextField txtDataPedido;

    @FXML
    private TextField txtIDCliPedido;

    @FXML
    private TextField txtIDPedido;

    @FXML
    private TextField txtIDProdPedido;

    @FXML
    private TextField txtNomePedido;

    @FXML
    private TextField txtQtdPedido;

    @FXML
    private TextField txtValorDescPedido;

    @FXML
    private TextField txtValorTotalPedido;

    @FXML
    void btAdicionarItemClickPedido(ActionEvent event) {
    }
    
    @FXML
    void btDeletarItemClickPedido(ActionEvent event) {
    }
    
    @FXML
    void btCancelarClickPedido(ActionEvent event) {
    }

    @FXML
    void btFinalizarClickPedido(ActionEvent event) {
    }

    @FXML
    void btNovoClickPedido(ActionEvent event) {
        camposEnabled(false);
        btEnabled(2);
    }

    @FXML
    void btPesquisarClickPedido(ActionEvent event) {
        listarProdutos();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    private void listarProdutos() {
        TableViewColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableViewColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableViewColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TableViewColumnQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        TableViewColumnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valortotal"));
    }

    private void camposEnabled(boolean b) {
        txtIDPedido.setDisable(true);
        txtDataPedido.setDisable(b);
        txtIDCliPedido.setDisable(true);
        txtNomePedido.setDisable(b);
        txtIDProdPedido.setDisable(true);
        txtQtdPedido.setDisable(b);
        txtValorDescPedido.setDisable(b);
        txtValorTotalPedido.setDisable(b);
        comboPedido.setDisable(b);        
    }

    private void btEnabled(int id) {
        switch (id) {
            case 1:
                btNovoPedido.setDisable(false);
                btCancelarPedido.setDisable(true);
                btFinalizarPedido.setDisable(true);
                btPesquisarPedido.setDisable(true);
                btAdicionarItemPedido.setDisable(true);
                btDeletarItemPedido.setDisable(true);
                break;
            case 2:
                btNovoPedido.setDisable(true);
                btCancelarPedido.setDisable(false);
                btFinalizarPedido.setDisable(false);
                btPesquisarPedido.setDisable(false);
                btAdicionarItemPedido.setDisable(false);
                btDeletarItemPedido.setDisable(false);
                break;
            default:
                btNovoPedido.setDisable(false);
                btCancelarPedido.setDisable(true);
                btFinalizarPedido.setDisable(true);
                btPesquisarPedido.setDisable(true);
                btAdicionarItemPedido.setDisable(true);
                btDeletarItemPedido.setDisable(true);
    }
    
}
