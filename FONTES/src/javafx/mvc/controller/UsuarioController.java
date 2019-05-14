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
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Marlon
 */
public class UsuarioController implements Initializable {

  @FXML
    private TextField txtIDCadUsuario;

    @FXML
    private ComboBox<?> ComboPesquisarUsuario;

    @FXML
    private TableColumn<?, ?> TableViewColumnNome;

    @FXML
    private Button btExcluirUsuario;

    @FXML
    private TextField txtPesquisarUsuario;

    @FXML
    private TableView<?> TableViewUsuario;

    @FXML
    private Button btSalvarUsuario;

    @FXML
    private Button btPesquisarUsuario;

    @FXML
    private Button btAlterarUsuario;

    @FXML
    private Button btInserirUsuario;

    @FXML
    private ComboBox<?> comboCadUsuario;

    @FXML
    private Button btFecharUsuario;
    
    @FXML
    private TextField txtCadUsuario;

    @FXML
    private Insets x1;

    @FXML
    private Insets x2;

    @FXML
    private TableColumn<?, ?> TableViewColumnID;

    @FXML
    private TextField txtCadSenha;

    @FXML
    void btPesquisarClickUsuario(ActionEvent event) {

    }

    @FXML
    void btInserirClickUsuario(ActionEvent event) {

    }

    @FXML
    void btSalvarClickUsuario(ActionEvent event) {

    }

    @FXML
    void btAlterarClickUsuario(ActionEvent event) {

    }

    @FXML
    void btExcluirClickExcluir(ActionEvent event) {

    }

    @FXML
    void btnFecharClickUsuario(ActionEvent event) {

    this.p.getChildren().remove(this);
        
    }
    private AnchorPane p;
    public void setTelaPrincipal(AnchorPane principal){
        this.p = principal;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }

}