/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.services;

import javafx.mvc.model.CaixaModel;

/**
 *
 * @author thiag
 */
public class CaixaAberto {

    private CaixaModel caixa;
    private static CaixaAberto instance = null;

    private CaixaAberto() {
    }

    public CaixaModel getCaixa() {
        return caixa;
    }

    public void setCaixa(CaixaModel caixa) {
        this.caixa = caixa;
    }

    public static CaixaAberto getInstance() {
        if (CaixaAberto.instance == null) {
            CaixaAberto.instance = new CaixaAberto();
        }
        return CaixaAberto.instance;
    }
}