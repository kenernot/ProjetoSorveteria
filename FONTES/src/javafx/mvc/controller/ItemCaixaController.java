/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.Main;
import javafx.mvc.contracts.Ouvinte;
import javafx.mvc.dao.ItemCaixaDao;
import javafx.mvc.events.EventoFecharTela;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.events.EventoTrocarTela;
import javafx.mvc.model.ItemCaixaModel;
import javafx.mvc.model.PedidoModel;
import javafx.mvc.services.CaixaAberto;
import javafx.mvc.services.Conexao;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author thiag
 */
public class ItemCaixaController implements Initializable, Ouvinte {

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_salvar;

    @FXML
    private ComboBox<String> cb_tipoMov;

    @FXML
    private TextField edt_valor;

    @FXML
    private TextArea txt_desc;

    @FXML
    void btnSalvarClick(ActionEvent event) {
        Alert mens = new Alert(Alert.AlertType.ERROR);
        Alert mens1 = new Alert(Alert.AlertType.INFORMATION);

        if (cb_tipoMov.getValue() == null) {
            mens.setContentText("Tipo movimento não pode ser vazio!!");
            mens.show();
        } else if (edt_valor.getText().isEmpty()) {
            mens.setContentText("Valor não pode ser vazio!!");
            mens.show();
        } else if (txt_desc.getText().isEmpty()) {
            mens.setContentText("Descrição não pode ser vazia!!");
            mens.show();
        } else {
            this.itemcaixa.setTipoMov((String) cb_tipoMov.getValue().substring(0, 1));
            this.itemcaixa.setValor(Double.valueOf(edt_valor.getText()));
            this.itemcaixa.setDescricao(txt_desc.getText());
            this.itemcaixa.setIdCaixa(CaixaAberto.getInstance().getCaixa().getIdCaixa());

            mens1.setContentText("Salvo com Sucesso!!");
            mens1.show();

            cb_tipoMov.setValue("");
            edt_valor.setText("");
            txt_desc.setText("");

            try {
                this.itd.salvar(this.itemcaixa);
                EventoOcorrido eventoAberto = new EventoOcorrido();
                eventoAberto.setSource(this);

                eventoAberto.setNome("ITEM_CAIXA_INSERIDO");
                Main.avisaOuvintes(eventoAberto);
            } catch (SQLException ex) {
                Logger.getLogger(ItemCaixaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void btnCancelarClick(ActionEvent event) {
        EventoOcorrido evento = new EventoFecharTela("ItemCaixa");
        evento.setSource(this);
        Main.avisaOuvintes(evento);
    }

    private ItemCaixaModel itemcaixa;
    private ItemCaixaDao itd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.subscribe(this);
        this.itd = new ItemCaixaDao(Conexao.getInstance().getConn());
        this.itemcaixa = new ItemCaixaModel();
        cb_tipoMov.getItems().clear();
        cb_tipoMov.getItems().add("Entrada");
        cb_tipoMov.getItems().add("Saida");
    }

    @Override
    public void avisandoAqui(EventoOcorrido evento) {
        if (evento.getNome().equals("PEDIDO_FINALIZADO")) {
            PedidoModel pm = new PedidoModel();
            pm = (PedidoModel) evento.getDados();

            this.itemcaixa.setIdPedido(pm.getIdPedido());
            cb_tipoMov.setValue("Entrada");
            edt_valor.setText(String.valueOf(pm.getValorTotal()));
            txt_desc.setText("Pedido realizado");
            
            EventoOcorrido evt = new EventoTrocarTela("ItemCaixa");
            evt.setSource(this);
            Main.avisaOuvintes(evt);
        }
    }

}
