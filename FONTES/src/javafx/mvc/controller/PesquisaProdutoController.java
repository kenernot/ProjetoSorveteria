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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ngnic
 */
public class PesquisaProdutoController implements Initializable {

    @FXML
    private TextField ftPesquisa;
    @FXML
    private Insets x1;
    @FXML
    private TextField ftDescicao;
    @FXML
    private Button btnFiltrar;
    @FXML
    private TableView<?> tableViewProduto;
    @FXML
    private TableColumn<?, ?> tableViewPeodutoNome;
    @FXML
    private TableColumn<?, ?> tableViewPeodutoDescricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnFiltrarClick(ActionEvent event) {
    }
    
}
