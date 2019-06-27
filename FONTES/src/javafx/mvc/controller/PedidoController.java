package javafx.mvc.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.mvc.Main;
import javafx.mvc.dao.ClienteDao;
import javafx.mvc.dao.InterfaceDAO;
import javafx.mvc.dao.PedidoDao;
import javafx.mvc.dao.ProdutoDao;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.events.EventoPedidoFinalizado;
import javafx.mvc.model.ClienteModel;
import javafx.mvc.model.ItemPedidoModel;
import javafx.mvc.model.PedidoModel;
import javafx.mvc.model.ProdutoModel;
import javafx.mvc.services.Conexao;
import javafx.mvc.services.UsuarioLogado;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private TableView<ItemPedidoModel> tableViewPedido;

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
    private Button btPesquisarCliente;

    @FXML
    private Button btPesquisarPedido;

    @FXML
    private ComboBox<String> comboPedido;

    @FXML
    private TextField txtDataPedido;
    
    @FXML
    private TextField txtDescPedido;

    @FXML
    private TextField txtIDCliPedido;

    @FXML
    private TextField txtIDPedido;

    @FXML
    private TextField txtIDProdPedido;

    @FXML
    private TextField txtQtdPedido;

    @FXML
    private TextField txtValorDescPedido;

    @FXML
    private TextField txtValorTotalPedido;

    private ProdutoModel produto;

    PesquisaProdutoController produtoController;
    private ObservableList<ItemPedidoModel> listaObserver;
    private ProdutoDao pd;
    private ArrayList<ItemPedidoModel> lista;

    @FXML
    void btAdicionarItemClickPedido(ActionEvent event) throws SQLException {
        if (!txtQtdPedido.getText().isEmpty()) {
            if (txtQtdPedido.getText().matches("^[0-9]*$") && !txtIDProdPedido.getText().isEmpty()) {
                TableViewColumnID.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
                TableViewColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
                TableViewColumnValor.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
                TableViewColumnQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
                TableViewColumnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));

                ItemPedidoModel pm = new ItemPedidoModel();
                pm.setIdProduto(Integer.parseInt(this.txtIDProdPedido.getText()));
                pm.setQtd(Double.parseDouble(this.txtQtdPedido.getText()));

                ProdutoModel prm = new ProdutoModel();
                ArrayList<ProdutoModel> myProduto = pd.buscar(" idProduto = " + txtIDProdPedido.getText());
                if (myProduto.size() > 0) {
                    prm = myProduto.get(0);
                }
                pm.setNomeProduto(prm.getNomeProduto());
                pm.setValorUnitario(prm.getValorVenda());

                Double valorTotal = pm.getQtd() * pm.getValorUnitario();
                pm.setValorTotal(valorTotal);

                lista.add(pm);
                listaObserver = FXCollections.observableArrayList(lista);

                tableViewPedido.setItems(listaObserver);

                txtValorTotalPedido.setText(String.valueOf(getSomaValorTotal()));
                txtIDProdPedido.clear();
                txtQtdPedido.clear();
            }
        }
    }

    @FXML
    void txtQtdPedidoReleased(KeyEvent event) {
        String txtAntes = txtQtdPedido.getText();
        if (!event.getText().matches("[0-9]")) {
            txtQtdPedido.setText(txtAntes.replaceAll("[^0-9]", ""));
        }
    }

    @FXML
    void txtDescPedidoReleased(KeyEvent event) {
        String txtAntes = txtValorDescPedido.getText();
        if (!event.getText().matches("[0-9]")) {
            txtValorDescPedido.setText(txtAntes.replaceAll("[^0-9]", ""));
        }
        txtValorTotalPedido.setText(String.valueOf(getSomaValorTotal()));
    }

    @FXML
    void btDeletarItemClickPedido(ActionEvent event) throws Exception {
        if (lista.size() != 0) {
            PedidoModel pedido = new PedidoModel();
            pedido.setIdPedido(Integer.parseInt(txtIDPedido.getText()));

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Deseja realmente excluir o item?");
            Optional<ButtonType> opcao = confirm.showAndWait();
            if (opcao.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
                Object obj = tableViewPedido.getSelectionModel().getSelectedItem();
                listaObserver.remove(obj);
                lista.remove(obj);
            }
        }
    }
    
    @FXML
    void btPesquisarClickCliente(ActionEvent event) {
    }

    //--------------CANCELAR OK--------------
    @FXML
    void btCancelarClickPedido(ActionEvent event) throws Exception {
        camposEnabled(true);
        btEnabled(1);
        limparCampos();
        listarProdutos();
    }

    //--------------FINALIZAR OK--------------
    @FXML
    void btFinalizarClickPedido(ActionEvent event) throws Exception {
        if (lista.size() != 0 && Double.parseDouble(txtValorTotalPedido.getText()) > 0) {
            if (txtValorDescPedido.getText().trim().isEmpty()) {
                txtValorDescPedido.setText("0");
            }
            PedidoModel pedido = new PedidoModel();
            ItemPedidoModel itemPedido = new ItemPedidoModel();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date oHoje = new Date();
            String hoje = sdf.format(oHoje);

            pedido.setIdPedido(Integer.parseInt(txtIDPedido.getText()));
            pedido.setDataPedido(hoje);
            pedido.setIdCliente(Integer.parseInt(txtIDCliPedido.getText()));
            pedido.setIdUsuario(UsuarioLogado.getInstance().getUser().getIdUsuario());
            pedido.setValorDesconto(Double.parseDouble(txtValorDescPedido.getText()));
            pedido.setValorTotal(Double.parseDouble(txtValorTotalPedido.getText()));

            String tipo = comboPedido.getValue();

            if (tipo.equals("Finalizado")) {
                tipo = "F";
            } else if (tipo.equals("Aberto")) {
                tipo = "A";
            } else if (tipo.equals("Em andamento")) {
                tipo = "E";
            }

            pedido.setStatus(tipo);

            pedido.setListaItemPedido(lista);
            PedidoDao pd = new PedidoDao(Conexao.getInstance().getConn());

            pd.salvar(pedido);

            camposEnabled(true);
            btEnabled(1);
            limparCampos();
            listarProdutos();
            lista.clear();
            listaObserver.clear();
            EventoOcorrido evt = new EventoPedidoFinalizado(pedido);
            evt.setSource(this);
            Main.avisaOuvintes(evt);

        }
    }

    @FXML
    void btNovoClickPedido(ActionEvent event) throws Exception {

        InterfaceDAO dao = new ClienteDao(Conexao.getInstance().getConn());

        if (dao.buscar(" 1=1 order by idcliente limit 1;").size() <= 0) {
            Alert deuPau = new Alert(Alert.AlertType.ERROR);
            deuPau.setContentText("Nenhum cliente cadastrado!\r\n Efetue o cadastro!");
            deuPau.show();
        } else {
            ClienteModel cliente = (ClienteModel) dao.buscar(" 1=1 order by idcliente limit 1;").get(0);
            camposEnabled(false);
            btEnabled(2);
            limparCampos();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date agora = new Date();
            String dataFormatada = sdf.format(agora);
            txtDataPedido.setText(dataFormatada);

            txtIDPedido.setText("0");

            comboPedido.setValue("Em andamento");

            txtIDCliPedido.setText(String.valueOf(cliente.getIdCliente()));
        }
    }

    @FXML
    void btPesquisarClickPedido(ActionEvent event) throws Exception {
        boolean okClicked = showDialog();

        if (okClicked) {
            this.txtIDProdPedido.setText(String.valueOf(produto.getIdProduto()));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Seleciona ! ! ! ! !   ");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista = new ArrayList<ItemPedidoModel>();
        listaObserver = FXCollections.observableArrayList(lista);
        btEnabled(1);
        camposEnabled(true);

        this.pd = new ProdutoDao(Conexao.getInstance().getConn());

        comboPedido.getItems().clear();
        comboPedido.getItems().add("Finalizado");
        comboPedido.getItems().add("Aberto");
        comboPedido.getItems().add("Em andamento");
        limparCampos();
    }

    private void listarProdutos() throws Exception {
        TableViewColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableViewColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableViewColumnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TableViewColumnQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        TableViewColumnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valortotal"));
    }

    public double getSomaValorTotal() {
        double resultado = 0;
        for (ItemPedidoModel item : lista) {
            resultado += item.getValorTotal();
        }
        if (isDouble(txtValorDescPedido.getText())) {
            resultado -= Double.valueOf(txtValorDescPedido.getText());
        }
        return resultado;
    }

    @FXML
    void valorDescontoKeyReleased(KeyEvent event) {
        txtValorTotalPedido.setText(String.valueOf(getSomaValorTotal()));
    }

    private void camposEnabled(boolean b) {
        txtIDPedido.setDisable(true);
        txtDataPedido.setDisable(true);
        txtIDCliPedido.setDisable(true);
        txtIDProdPedido.setDisable(true);
        txtQtdPedido.setDisable(b);
        txtValorDescPedido.setDisable(b);
        txtValorTotalPedido.setDisable(true);
        txtDescPedido.setDisable(true);
        comboPedido.setDisable(true);
        tableViewPedido.setDisable(b);
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
                btPesquisarCliente.setDisable(true);
                break;
            case 2:
                btNovoPedido.setDisable(true);
                btCancelarPedido.setDisable(false);
                btFinalizarPedido.setDisable(false);
                btPesquisarPedido.setDisable(false);
                btAdicionarItemPedido.setDisable(false);
                btDeletarItemPedido.setDisable(false);
                btPesquisarCliente.setDisable(false);
                break;
            default:
                btNovoPedido.setDisable(false);
                btCancelarPedido.setDisable(true);
                btFinalizarPedido.setDisable(true);
                btPesquisarPedido.setDisable(true);
                btPesquisarCliente.setDisable(true);
                btAdicionarItemPedido.setDisable(true);
                btDeletarItemPedido.setDisable(true);
        }
    }

    private void limparCampos() {
        txtIDPedido.setText("");
        txtDataPedido.setText("");
        txtIDCliPedido.setText("");
        txtIDProdPedido.setText("");
        txtQtdPedido.setText("");
        txtValorDescPedido.setText("");
        txtValorTotalPedido.setText("");
        txtDescPedido.setText("");
        lista.clear();
        listaObserver.clear();
    }

    private boolean showDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(PesquisaProdutoController.class.getResource("/javafx/mvc/view/PesquisaProduto.fxml"));

        AnchorPane anchor = (AnchorPane) loader.load();
        Scene scene = new Scene(anchor);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Busca de produto!");
        dialogStage.setScene(scene);

        this.produtoController = loader.getController();

        this.produtoController.setDialogStage(dialogStage);

        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.showAndWait();

        this.produto = this.produtoController.getProduto();

        return this.produtoController.isOkClicked();
    }

    public boolean isDouble(String s) {
        boolean bool = true;
        try {
            Double.parseDouble(s);
        } catch (Exception ex) {
            bool = false;
            return bool;
        }

        return bool;
    }

}
