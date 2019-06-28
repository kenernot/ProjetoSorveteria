/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.mvc.Main;
import javafx.mvc.dao.UsuarioDao;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.events.EventoTrocarTela;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.Conexao;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Marlon
 */
public class UsuarioController implements Initializable {

 
    @FXML
    private ComboBox<String> ComboPesquisarUsuario;

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
    private Button btnAlteraSenha;

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
    private TabPane TabPane;

    @FXML
    void btPesquisarClickUsuario(ActionEvent event) throws Exception {
        listarUsuario();
    }
    
     @FXML
    void btnAlteraSenhaClick(ActionEvent event) throws IOException {
        //this.chama(um);
        EventoTrocarTela et = new EventoTrocarTela("AlterarSenha"); 
         EventoOcorrido eo = new EventoOcorrido(); 
         eo.setNome("AlterarSenha");
         eo.setDados(um); 
         Main.avisaOuvintes(eo); 
         Main.avisaOuvintes(et); 
    }

    @FXML
    void btInserirClickUsuario(ActionEvent event) throws Exception {
        this.habilitar();
    }
    
    private UsuarioModel um;
    
    @FXML
    void btSalvarClickUsuario(ActionEvent event) throws Exception{
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNomeFuncionario(txtCadNomeUsuario.getText().trim().toUpperCase());
        usuario.setNomeUsuario(txtCadUsuario.getText().trim().toUpperCase());
        usuario.setSenhaUsuario(txtCadSenhaUsuario.getText().trim().toUpperCase());
        btnAlteraSenha.setDisable(true);
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
                /*this.ud.remove(um);*/
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
        this.ComboPesquisarUsuario.getItems().add("Ativo"); 
        this.ComboPesquisarUsuario.getItems().add("Inativo"); 
        this.ComboPesquisarUsuario.getItems().add("Todos"); 
        
    }
    
    private UsuarioDao ud;
    private ObservableList<UsuarioModel> listaObserver;
    
    
    private void listarUsuario() throws Exception{
        
        TableViewColumnID.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        TableViewColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
        TableViewColumnNomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
        TableViewColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        String criterio = " 1=1 "; 
        
        if (!txtPesquisarUsuario.getText().trim().isEmpty()) {
            //falta filtrar o combo de status na pesquisa
            String par = txtPesquisarUsuario.getText(); 
            if (!par.matches("[^´~%';-_\\/\\*]+")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Caracteres inválidos!");
                alert.show();
                return;
            } 
            criterio += " and nomeUsuario LIKE '%" + par + "%'";
        } 
        if (ComboPesquisarUsuario.getValue()!=null) {
            
        if (ComboPesquisarUsuario.getValue().equals("Ativo")) {
            criterio += " and status = 'A'";
        }
        if (ComboPesquisarUsuario.getValue().equals("Inativo")) {
            criterio += " and status = 'I'"; 
         } 
        }
        System.out.println(criterio);
        
        ArrayList<UsuarioModel> buscar = this.ud.buscar(criterio); 
        listaObserver = FXCollections.observableArrayList();
        listaObserver.clear();
        listaObserver.addAll(buscar); 
        TableViewUsuario.setItems(listaObserver);
    }

    private void habilitar() {
        this.APCampos.setDisable(false);
       
    }

    private void Desabilitar() {
        this.APCampos.setDisable(true);
    }

    @FXML
    private void TbAlterarClick(MouseEvent event) {
        if (event.getClickCount()>=2) { 
            UsuarioModel selectedItem = TableViewUsuario.getSelectionModel().getSelectedItem(); 
            txtCadNomeUsuario.setText(selectedItem.getNomeFuncionario());
            btnAlteraSenha.setDisable(false);
            txtCadUsuario.setText(selectedItem.getNomeUsuario()); 
            txtIDCadUsuario.setText(Integer.toString(selectedItem.getIdUsuario())); 
            comboCadUsuario.setValue(selectedItem.getStatus()); 
            checkPermitirCliente.setSelected(selectedItem.getCliente().equals("T"));
            checkPermitirProduto.setSelected(selectedItem.getProduto().equals("T")); 
            checkPermitirUsuario.setSelected(selectedItem.getUsuario().equals("T"));
            TabPane.getSelectionModel().select(1);
        }
    }
    
    private boolean chama(UsuarioModel usu) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(
                AlterarSenhaController.class
                .getResource("/javafx/mvc/view/AlterarSenha.fxml")
        );
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edição de usuário");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        return isOkClicked();

}
    private boolean okClicked = false;
    private boolean isOkClicked() {
        
        return okClicked; 
    }
}
