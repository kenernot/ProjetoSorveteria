/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.dao.ItemCaixaDao;
import javafx.mvc.model.CaixaModel;
import javafx.mvc.model.ItemCaixaModel;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.CaixaAberto;
import javafx.mvc.services.Conexao;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class FechamentoCaixaController implements Initializable {

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_salvar;

    @FXML
    private TextField edt_entradas;

    @FXML
    private TextField edt_informarSaldo;

    @FXML
    private TextField edt_saidas;

    @FXML
    private TextField edt_saldoFinal;

    @FXML
    void btnSalvarClick(ActionEvent event) {
        this.caixa.setValorFinal(Double.valueOf(edt_saldoFinal.getText()));
    }

    @FXML
    void btnCancelarClick(ActionEvent event) {
    }

    private CaixaModel caixa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.caixa = CaixaAberto.getInstance().getCaixa();
        ItemCaixaDao icd = new ItemCaixaDao(Conexao.getInstance().getConn());

        try {
            icd.buscar("idCaixa=" + this.caixa.getIdCaixa());
            List<ItemCaixaModel> resultadosDao = (List<ItemCaixaModel>) icd.buscar("idCaixa=" + this.caixa.getIdCaixa());
        } catch (SQLException ex) {
            Logger.getLogger(FechamentoCaixaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
