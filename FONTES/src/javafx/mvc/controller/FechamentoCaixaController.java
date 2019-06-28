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
import javafx.mvc.Main;
import javafx.mvc.contracts.Ouvinte;
import javafx.mvc.dao.CaixaDao;
import javafx.mvc.dao.ItemCaixaDao;
import javafx.mvc.events.EventoFecharTela;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.model.CaixaModel;
import javafx.mvc.model.ItemCaixaModel;
import javafx.mvc.services.CaixaAberto;
import javafx.mvc.services.Conexao;
import javafx.mvc.services.UsuarioLogado;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class FechamentoCaixaController implements Initializable, Ouvinte {

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
        Alert mens1 = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(this.caixa.getIdCaixa());
        this.caixa.setValorFinal(Double.valueOf(edt_saldoFinal.getText()));
        this.caixa.setInformarSaldo(Double.valueOf(edt_informarSaldo.getText()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date hoje = new Date();
        String dHoje = sdf.format(hoje);
        this.caixa.setDataFechamento(dHoje);
        this.cd.salvar(this.caixa);
        mens1.setContentText("Caixa Fechado com Sucesso!!");
        mens1.show();
        EventoOcorrido evento = new EventoFecharTela("FechamentoCaixa");
        evento.setSource(this);
        Main.avisaOuvintes(evento);
    }

    @FXML
    void btnCancelarClick(ActionEvent event) {
        EventoOcorrido evento = new EventoFecharTela("FechamentoCaixa");
        evento.setSource(this);
        Main.avisaOuvintes(evento);
    }

    private CaixaModel caixa;
    private CaixaDao cd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.subscribe(this);
        cd = new CaixaDao(Conexao.getInstance().getConn());
        try {
            if (CaixaAberto.getInstance().getCaixa() == null) {
                setSingletonCaixa();
            }
        } catch (SQLException ex) {
            System.out.println("Deu ruimzao");
        }

        atualizaCampos();
    }

    private void setSingletonCaixa() throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date hoje = new Date();
        String dHoje = sdf.format(hoje);

        ArrayList<CaixaModel> arrayCaixa = new ArrayList<>();
        arrayCaixa = cd.buscar("idUsuario = " + UsuarioLogado.getInstance().getUser().getIdUsuario() + " and dataAbertura like concat('%',date_format(now(),'%Y-%m-%d'),'%') ORDER BY idCaixa DESC limit 1");
        if (arrayCaixa.size() > 0) {
            CaixaAberto.getInstance().setCaixa(arrayCaixa.get(0));
            System.out.println(arrayCaixa.get(0).getIdCaixa());
        }
    }

    @Override
    public void avisandoAqui(EventoOcorrido evento) {
        if (evento.getNome().equals("CAIXA_ABERTO") || evento.getNome().equals("ITEM_CAIXA_INSERIDO")) {
            atualizaCampos();
        }
    }

    private void atualizaCampos() {
        this.caixa = CaixaAberto.getInstance().getCaixa();
        ItemCaixaDao icd = new ItemCaixaDao(Conexao.getInstance().getConn());
        if (this.caixa != null) {
            System.out.println("Caixa nao esta vazio");
            try {
                icd.buscar("idCaixa=" + this.caixa.getIdCaixa());
                List<ItemCaixaModel> resultadosDao = (List<ItemCaixaModel>) icd.buscar("idCaixa=" + this.caixa.getIdCaixa());

                Double entradas = 0.0;
                Double saidas = 0.0;
                for (ItemCaixaModel item : resultadosDao) {
                    if (item.getTipoMov().toUpperCase().equals("E")) {
                        entradas += item.getValor();
                    } else if (item.getTipoMov().toUpperCase().equals("S")) {
                        saidas += item.getValor();
                    }
                }

                Double saldoF = this.caixa.getValorAbertura() + entradas - saidas;
                edt_entradas.setText(entradas.toString());
                edt_saidas.setText(saidas.toString());
                edt_saldoFinal.setText(saldoF.toString());
                edt_entradas.setDisable(true);
                edt_saidas.setDisable(true);
                edt_saldoFinal.setDisable(true);

            } catch (SQLException ex) {
                Logger.getLogger(FechamentoCaixaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
