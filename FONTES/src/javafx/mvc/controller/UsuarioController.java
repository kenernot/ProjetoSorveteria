/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.mvc.dao.UsuarioDao;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.Conexao;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Marlon
 */
public class UsuarioController implements Initializable {

 
    @FXML
    private ComboBox<?> ComboPesquisarUsuario;

    @FXML
    private TableColumn<?, ?> TableViewColumnID;

    @FXML
    private TableColumn<?, ?> TableViewColumnNome;

    @FXML
    private TableColumn<?, ?> TableViewColumnNomeUsuario;

    @FXML
    private TableColumn<?, ?> TableViewColumnStatus;

    @FXML
    private TableView<UsuarioModel> TableViewUsuario;

    @FXML
    private Button btAlterarUsuario;

    @FXML
    private Button btExcluirUsuario;

    @FXML
    private Button btFecharUsuario;

    @FXML
    private Button btInserirUsuario;

    @FXML
    private Button btPesquisarUsuario;
    
     @FXML
    private Button btnCancelarUsuario;

    @FXML
    private Button btSalvarUsuario;

    @FXML
    private CheckBox checkPermitirCliente;

    @FXML
    private CheckBox checkPermitirProduto;

    @FXML
    private CheckBox checkPermitirUsuario;

    @FXML
    private ComboBox<String> comboCadUsuario;

    @FXML
    private TextField txtCadNomeUsuario;

    @FXML
    private TextField txtCadSenhaUsuario;

    @FXML
    private TextField txtCadUsuario;

    @FXML
    private TextField txtIDCadUsuario;

    @FXML
    private TextField txtPesquisarUsuario;
    @FXML
    private Font x3;
    @FXML
    private Insets x1;
    @FXML
    private Insets x2;
    @FXML
    private AnchorPane APCampos;

    @FXML
    void btPesquisarClickUsuario(ActionEvent event) throws Exception {
        listarUsuario();
    }

    @FXML
    void btInserirClickUsuario(ActionEvent event) throws Exception {
        this.habilitar();
    }
    
    private UsuarioModel um;
    
    @FXML
    void btSalvarClickUsuario(ActionEvent event) throws Exception{
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNomeFuncionario(txtCadNomeUsuario.getText());
        usuario.setNomeUsuario(txtCadUsuario.getText());
        usuario.setSenhaUsuario(txtCadSenhaUsuario.getText());
        usuario.setStatus(comboCadUsuario.getValue().substring(0, 1).toUpperCase()); 
       usuario.setCliente(Boolean.valueOf(checkPermitirCliente.isSelected()).toString().substring(0, 1).toUpperCase());
      usuario.setUsuario(Boolean.valueOf(checkPermitirUsuario.isSelected()).toString().substring(0, 1).toUpperCase());
       usuario.setProduto(Boolean.valueOf(checkPermitirProduto.isSelected()).toString().substring(0, 1).toUpperCase());
                
       this.ud.salvar(usuario);

    }

    @FXML
    void btAlterarClickUsuario(ActionEvent event) throws Exception{
        this.ud.salvar(um);
    }

    @FXML
    void btExcluirClickExcluir(ActionEvent event)throws Exception {
        UsuarioModel um = (UsuarioModel) TableViewUsuario.getSelectionModel().getSelectedItem();
        if (um != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText("Deseja excluir?");
            Optional<ButtonType> opcao = confirm.showAndWait();
            if(opcao.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
                this.ud.remove(um);
                listarUsuario();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Seleciona!!!");
            alert.show();
        
        }
        
    }
    
     @FXML
    void btnCancelarClickUsuario(ActionEvent event) {
        this.Desabilitar(); 
    }


    @FXML
    void btnFecharClickUsuario(ActionEvent event) {

        this.p.getChildren().remove(this);

    }
    private AnchorPane p;

    public void setTelaPrincipal(AnchorPane principal) {
        this.p = principal;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.ud = new UsuarioDao(Conexao.getInstance().getConn()); 
        this.comboCadUsuario.getItems().add("Ativo");
        this.comboCadUsuario.getItems().add("Inativo");
    }
    
    private UsuarioDao ud;
    private ObservableList<UsuarioModel> listaObserver;
    
    
    private void listarUsuario() throws Exception{
        
        TableViewColumnID.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        TableViewColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
        TableViewColumnNomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
        TableViewColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        String criterio = ""; 
        
        if (!txtPesquisarUsuario.getText().trim().isEmpty()) {
            
            String par = txtPesquisarUsuario.getText(); 
            if (!par.matches("[^´~%';-_\\/\\*]+")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Caracteres inválidos!");
                alert.show();
                return;
            }
            
            criterio = " where nomeUsuario like '%" + par + "%'";
        }
        ArrayList<UsuarioModel> buscar = this.ud.buscar(criterio); 
        listaObserver = FXCollections.observableArrayList();
        listaObserver.clear();
        listaObserver.addAll(buscar); 
        TableViewUsuario.setItems(listaObserver);
    }

    private void habilitar() {
        this.APCampos.setDisable(false);
        //o cancelar será setDisable como true
    }

    private void Desabilitar() {
        this.APCampos.setDisable(true);
    }

}
