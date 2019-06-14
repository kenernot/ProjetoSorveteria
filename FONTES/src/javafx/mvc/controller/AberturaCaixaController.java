/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.dao.CaixaDao;
import javafx.mvc.model.CaixaModel;
import javafx.mvc.services.CaixaAberto;
import javafx.mvc.services.Conexao;
import javafx.mvc.services.UsuarioLogado;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class AberturaCaixaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField edt_valorInic;
    
    @FXML
    private Button btn_salvar;
    
      @FXML
    void btnSalvarClick(ActionEvent event) throws SQLException {
        CaixaDao dao = new CaixaDao(Conexao.getInstance().getConn());
        this.caixa.setValorAbertura(Double.valueOf(edt_valorInic.getText()));
        CaixaModel cm = dao.salvar(this.caixa);
          System.out.println(cm.getIdCaixa());
        this.caixa.setIdCaixa(cm.getIdCaixa());
        CaixaAberto.getInstance().setCaixa(this.caixa);
    }
    private CaixaModel caixa;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      this.caixa = new CaixaModel();
      this.caixa.setIdUsuario(UsuarioLogado.getInstance().getUser().getIdUsuario());
    }    
    
}
