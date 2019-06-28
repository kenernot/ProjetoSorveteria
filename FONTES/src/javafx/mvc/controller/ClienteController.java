/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.awt.event.FocusEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.mvc.dao.ClienteDao;
import javafx.mvc.model.ClienteModel;
import javafx.mvc.services.Conexao;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private Button btCancelar;

    @FXML
    private Button btInserir;

    @FXML
    private Button btPesquisar;

    @FXML
    private Button btSalvar;

    @FXML
    private ComboBox<String> cbGenero;

    @FXML
    private ComboBox<String> cbStatus;

    @FXML
    private TabPane tabCliente;

    @FXML
    private TableColumn<?, ?> tableViewCPF;

    @FXML
    private TableColumn<?, ?> tableViewCelular;

    @FXML
    private TableView<ClienteModel> tableViewCliente;

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
    void btCancelarClick(ActionEvent event) {

        limparCampos();
        tabSelected(0);
        campoEnabled(true);
        btEnabled(1);
    }

    @FXML
    void btAlterarClick(ActionEvent event) throws SQLException, Exception {
        campoEnabled(false);
        btEnabled(2);
    }

    @FXML
    void btExcluirClick(ActionEvent event) throws SQLException, Exception {
        this.cm.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
        this.cm.setNomeCliente(txtNomeCliente.getText());
        this.cm.setCpf(txtCpf.getText());
        this.cm.setRg(txtRg.getText());
        this.cm.setGenero(cbGenero.getSelectionModel().getSelectedItem().toString());
        this.cm.setCelular(txtCelular.getText());
        this.cm.setStatus("I");

        // Manda Pro Banco
        this.dc.salvar(this.cm);
        btEnabled(1);
        limparCampos();
        listarCliente();
        campoEnabled(true);
    }

    @FXML
    void btInserirClick(ActionEvent event) throws Exception {
        campoEnabled(false);
        btEnabled(2);
        limparCampos();

        txtIdCliente.setText("0");
        cbStatus.getSelectionModel().selectFirst();
        cbGenero.getSelectionModel().selectFirst();
        txtNomeCliente.requestFocus();

    }

    private ClienteDao dc;
    private List<ClienteModel> lista;
    private ObservableList<ClienteModel> listaObserver;

    @FXML
    void btPesquisarClick(ActionEvent event) throws Exception {
        listarCliente();
    }

    private ClienteModel cm;

    @FXML
    void btSalvarClick(ActionEvent event) throws SQLException, Exception {

        if (txtNomeCliente.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Campo Nome Vazio");
            alert.show();
            txtNomeCliente.requestFocus();
            return;

        } else if (!txtNomeCliente.getText().trim().matches("[A-Za-z0-9çãáéíóõúÇÃÕÁÉÍÓÚ]+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Caracteres Inválidos");
            alert.show();
            txtNomeCliente.requestFocus();
            return;
        } else if (txtCpf.getText().trim().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Campo CPF Vazio");
            alert.show();
            txtCpf.requestFocus();
            return;
        } else if (!txtCpf.getText().trim().matches("[0-9\\.-]+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("O CPF é composto apenas de números");
            alert.show();
            txtCpf.requestFocus();
            return;
        } else if (!txtCpf.getText().trim().matches("[0-9]{11}") && !txtCpf.getText().trim().matches("[0-9\\.-]{14}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Quantidade de caracteres Inválida!Favor, informar a quantidade de 11 ou 14 digitos.");
            alert.show();
            txtCpf.requestFocus();
            return;

        }
        String sql = "cpf = '" + String.valueOf(txtCpf.getText().trim().replaceFirst("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4")) + "'";
        ArrayList<ClienteModel> buscar = this.dc.buscar(sql);
        if (buscar.size() != 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("CPF já cadastrado, favor informar outro.");
            alert.show();
            return;
        }

        this.cm.setIdCliente(Integer.parseInt(txtIdCliente.getText()));
        this.cm.setNomeCliente(txtNomeCliente.getText());
        this.cm.setCpf(String.valueOf(txtCpf.getText()).replaceFirst("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4"));
        this.cm.setRg(txtRg.getText());
        this.cm.setGenero(cbGenero.getSelectionModel().getSelectedItem().toString());
        this.cm.setCelular(txtCelular.getText());
        this.cm.setStatus(cbStatus.getSelectionModel().getSelectedItem().toString());
        // Manda Pro Banco
        this.dc.salvar(this.cm);
        btEnabled(1);
        limparCampos();
        listarCliente();
        campoEnabled(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.dc = new ClienteDao(Conexao.getInstance().getConn());
        this.cm = new ClienteModel();
        btEnabled(1);
        campoEnabled(true);
        try {
            listarCliente();
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableViewCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newvalue) -> selecionaCliente(newvalue));
    }

    private void campoEnabled(boolean b) {
        txtNomeCliente.setDisable(b);
        txtCpf.setDisable(b);
        txtRg.setDisable(b);
        cbGenero.setDisable(b);
        txtCelular.setDisable(b);
        cbStatus.setDisable(b);
    }

    private void btEnabled(int id) {
        switch (id) {
            case 1:
                btInserir.setDisable(false);
                btSalvar.setDisable(true);
                btAlterar.setDisable(true);
                btExcluir.setDisable(true);
                btCancelar.setDisable(true);
                break;
            case 2:
                btInserir.setDisable(true);
                btSalvar.setDisable(false);
                btAlterar.setDisable(true);
                btExcluir.setDisable(true);
                btCancelar.setDisable(false);
                break;
            case 3:
                btInserir.setDisable(true);
                btSalvar.setDisable(true);
                btAlterar.setDisable(false);
                btExcluir.setDisable(false);
                btCancelar.setDisable(false);
                break;
        }
    }

    private void limparCampos() {
        txtIdCliente.setText("");
        txtNomeCliente.setText("");
        txtCpf.setText("");
        txtRg.setText("");
        txtCelular.setText("");

        ArrayList<String> opcoesGender = new ArrayList<String>();

        opcoesGender.add("F");
        opcoesGender.add("M");
        opcoesGender.add("O");
        cbGenero.setItems(FXCollections.observableArrayList(opcoesGender));

        ArrayList<String> opcoesStatus = new ArrayList<String>();
        opcoesStatus.add("A");
        opcoesStatus.add("I");
        cbStatus.setItems(FXCollections.observableArrayList(opcoesStatus));

        cbStatus.getSelectionModel().selectFirst();
        cbGenero.getSelectionModel().selectFirst();
    }

    private void listarCliente() throws Exception {

        tableViewIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        tableViewNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableViewCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tableViewRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        tableViewGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tableViewCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
        tableViewStatusCliente.setCellValueFactory(new PropertyValueFactory<>("status"));
        String sql = ("");
        if (!txtPesquisarNomeCliente.getText().trim().isEmpty() || !txtPesquisarCpf.getText().trim().isEmpty()) {
            String par = ("");

            if (!txtPesquisarCpf.getText().trim().isEmpty()) {
                par = txtPesquisarCpf.getText().trim();
                sql = "cpf = " + par;
            } else {
                par = txtPesquisarNomeCliente.getText().trim();
                sql = "nomeCliente like '%" + par + "%'";
            }

            if (!par.matches("[A-Za-z0-9]+")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Caracteres Inválidos");
                alert.show();
                return;
            }
        }

        lista = (List<ClienteModel>) dc.buscar(sql);
        listaObserver = FXCollections.observableArrayList(lista);

        tableViewCliente.setItems(listaObserver);
    }

    private void selecionaCliente(ClienteModel c) {
        if (c != null) {
            txtIdCliente.setText(String.valueOf(c.getIdCliente()));
            txtNomeCliente.setText(c.getNomeCliente());
            txtCpf.setText(c.getCpf());
            txtRg.setText(c.getRg());
            cbStatus.setValue(c.getStatus());
            txtCelular.setText(c.getCelular());
            cbGenero.setValue(c.getGenero());

            tabSelected(1);
            campoEnabled(true);
            btEnabled(3);
        }
    }

    private void tabSelected(int id) {
        SingleSelectionModel<Tab> tabModel = tabCliente.getSelectionModel();
        tabModel.select(id);
    }

}
