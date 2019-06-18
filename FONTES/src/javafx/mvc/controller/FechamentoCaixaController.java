/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.dao.CaixaDao;
import javafx.mvc.dao.ItemCaixaDao;
import javafx.mvc.model.CaixaModel;
import javafx.mvc.model.ItemCaixaModel;
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
    void btnSalvarClick(ActionEvent event) throws SQLException {
        System.out.println(this.caixa.getIdCaixa());
        this.caixa.setValorFinal(Double.valueOf(edt_saldoFinal.getText()));
        this.caixa.setInformarSaldo(Double.valueOf(edt_informarSaldo.getText()));
        this.cd.salvar(this.caixa);
    }

    @FXML
    void btnCancelarClick(ActionEvent event) {

    }

    private CaixaModel caixa;
    private CaixaDao cd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cd = new CaixaDao(Conexao.getInstance().getConn());
        try {
            setSingletonCaixa();
        } catch (SQLException ex) {
            System.out.println("Deu ruimzao");
        }

        this.caixa = CaixaAberto.getInstance().getCaixa();
        ItemCaixaDao icd = new ItemCaixaDao(Conexao.getInstance().getConn());
        if (this.caixa != null) {
            try {
                icd.buscar("idCaixa=" + this.caixa.getIdCaixa());
                List<ItemCaixaModel> resultadosDao = (List<ItemCaixaModel>) icd.buscar("idCaixa=" + this.caixa.getIdCaixa());
            } catch (SQLException ex) {
                Logger.getLogger(FechamentoCaixaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setSingletonCaixa() throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hoje = new Date();
        String dHoje = sdf.format(hoje);

        ArrayList<CaixaModel> arrayCaixa = new ArrayList<>();
        arrayCaixa = cd.buscar("dataAbertura like '%" + dHoje + "%' AND idUsuario = " + UsuarioLogado.getInstance().getUser().getIdUsuario() + " limit 1 ");
        if (arrayCaixa.size() > 0) {
            CaixaAberto.getInstance().setCaixa(arrayCaixa.get(0));
            System.out.println(arrayCaixa.get(0).getIdCaixa());
        }
    }
}
