/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.Main;
import javafx.mvc.contracts.Ouvinte;
import javafx.mvc.dao.CaixaDao;
import javafx.mvc.events.EventoFecharTela;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.events.EventoTrocarTela;
import javafx.mvc.model.CaixaModel;
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
public class AberturaCaixaController implements Initializable, Ouvinte {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField edt_valorInic;

    @FXML
    private Button btn_salvar;

    @FXML
    void btnSalvarClick(ActionEvent event) throws SQLException {
        Alert mens = new Alert(Alert.AlertType.ERROR);
        Alert mens1 = new Alert(Alert.AlertType.INFORMATION);

        if (edt_valorInic.getText().isEmpty()) {
            mens.setContentText("Valor Inicial não pode ser vazio!!");
            mens.show();
        } else if (Integer.parseInt(edt_valorInic.getText()) < 0) {
            mens.setContentText("Valor Inicial não pode ser negativo!!");
            mens.show();
        } else {
            CaixaDao dao = new CaixaDao(Conexao.getInstance().getConn());
            this.caixa.setValorAbertura(Double.valueOf(edt_valorInic.getText()));
            CaixaModel cm = dao.salvar(this.caixa);
            System.out.println(cm.getIdCaixa());
            this.caixa.setIdCaixa(cm.getIdCaixa());
            CaixaAberto.getInstance().setCaixa(this.caixa);

            mens1.setContentText("Salvo com Sucesso!!");
            mens1.show();
            
            EventoOcorrido eventoAberto = new EventoOcorrido();
            eventoAberto.setSource(this);
            
            eventoAberto.setNome("CAIXA_ABERTO");
            Main.avisaOuvintes(eventoAberto);
            
            EventoOcorrido eventofechar = new EventoFecharTela("AberturaCaixa");
            eventofechar.setSource(this);
            Main.avisaOuvintes(eventofechar);
            
        }
    }
    private CaixaModel caixa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.caixa = new CaixaModel();
        this.caixa.setIdUsuario(UsuarioLogado.getInstance().getUser().getIdUsuario());
        Main.subscribe(this);
    }

    @Override
    public void avisandoAqui(EventoOcorrido evento) {
        if (evento.getNome().equals("LOGIN_EFETUADO")) {
            CaixaDao dao = new CaixaDao(Conexao.getInstance().getConn());
            ArrayList<CaixaModel> lista = new ArrayList<>();
            try {
                lista = dao.buscar("idUsuario = " + UsuarioLogado.getInstance().getUser().getIdUsuario() + " and dataAbertura like concat('%',date_format(now(),'%Y-%m-%d'),'%') ORDER BY idCaixa DESC limit 1");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            if (lista.size() == 0) {
                EventoOcorrido evento1 = new EventoTrocarTela("AberturaCaixa");
                evento.setSource(this);
                Main.avisaOuvintes(evento1);
            } else if (lista.get(0).getDataFechamento() == null) {
                System.out.println("Caixa já aberto!");
                CaixaAberto.getInstance().setCaixa(lista.get(0));
            } else {
                System.out.println("Caixa fechado!");
                CaixaAberto.getInstance().setCaixa(lista.get(0));
            }

        }
    }

}
