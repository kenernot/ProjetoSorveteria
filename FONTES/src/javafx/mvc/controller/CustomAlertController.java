/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author drink
 */
public class CustomAlertController implements Initializable {

    @FXML
    private AnchorPane alertPane;
    @FXML
    private Button btnOk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Exemplo de transição com opacidade
        this.alertPane.setOpacity(0);
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setNode(alertPane);
        ft.play();
        // Exemplo de transição de translação
        TranslateTransition tt = new TranslateTransition();
        tt.setFromX(-1000);
        tt.setToX(0);
        tt.setDuration(Duration.millis(500));
        tt.setNode(alertPane);
        tt.play();
    }

    @FXML
    private void btnOkCkick(ActionEvent event) {
        TranslateTransition tt = new TranslateTransition();
        tt.setToX(-1000);
        tt.setFromX(0);
        tt.setDuration(Duration.millis(500));
        tt.setNode(alertPane);
        tt.play();
        FadeTransition ft = new FadeTransition();
        ft.setDuration(Duration.millis(500));
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.onFinishedProperty().addListener((observable) -> {
            // exemplo de fechamento da tela 
            AnchorPane parent = (AnchorPane) btnOk.getScene().getRoot().getParent();
            parent.getChildren().remove(btnOk.getScene().getRoot());
        });
        ft.setNode(alertPane);
        ft.play();
    }

}
