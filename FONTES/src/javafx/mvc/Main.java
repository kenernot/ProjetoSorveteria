/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.mvc.contracts.Ouvinte;
import javafx.mvc.controller.InitialConfigController;
import javafx.mvc.controller.LoginController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.mvc.controller.PrincipalController;
import javafx.mvc.events.EventoOcorrido;
import javafx.mvc.events.EventoPedidoFinalizado;
import javafx.mvc.events.LoginEfetuado;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 *
 * @author Marlon
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(PrincipalController.class.getResource("/javafx/mvc/view/Principal.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        PrincipalController c = loader.getController();
        c.setStagePrincipal(primaryStage);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Sistema sorveteria");
        primaryStage.show();

        //
        //verifica db.properties
        File file = new File("db.properties");
        if (!file.exists()) {
            Stage initialConfig = new Stage();
            FXMLLoader loaderConfig = new FXMLLoader();
            loaderConfig.setLocation(InitialConfigController.class.getResource("/javafx/mvc/view/InitialConfig.fxml"));
            AnchorPane pageConfig = (AnchorPane) loaderConfig.load();
            initialConfig.setTitle("Config");
            Scene sceneConfig = new Scene(pageConfig);
            initialConfig.setScene(sceneConfig);

            InitialConfigController cConfig = loaderConfig.getController();
            cConfig.setDialogStage(initialConfig);

            initialConfig.initOwner(primaryStage);
            initialConfig.initModality(Modality.APPLICATION_MODAL);
            initialConfig.showAndWait();

            if (!cConfig.isFinished()) {
                primaryStage.close();
            } else {

            }
        }

        // Abre a tela validar o login
        Stage login = new Stage();
        FXMLLoader loaderLogin = new FXMLLoader();
        loaderLogin.setLocation(LoginController.class.getResource("/javafx/mvc/view/Login.fxml"));
        AnchorPane pageLogin = (AnchorPane) loaderLogin.load();
        login.setTitle("Login");
        Scene sceneLogin = new Scene(pageLogin);
        login.setScene(sceneLogin);

        LoginController cLogin = loaderLogin.getController();
        cLogin.setDialogStage(login);

        login.initOwner(primaryStage);
        login.initModality(Modality.APPLICATION_MODAL);
        login.showAndWait();

        if (!cLogin.isIsAllowed()) {
            primaryStage.close();
        } else {
            c.setLabelUsuario();
            c.setScenes();
            
            EventoOcorrido evt = new LoginEfetuado();
            evt.setSource(this);
            avisaOuvintes(evt);
        }

    }

    private static Set<Ouvinte> ouvintes;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.ouvintes = new HashSet<>();
        launch(args);
        
    }

    public static void subscribe(Ouvinte ouvinte) {
        Main.ouvintes.add(ouvinte);
    }

    public static void unsubscribe(Ouvinte ouvinte) {
        Main.ouvintes.remove(ouvinte);
    }

    public static void avisaOuvintes(EventoOcorrido evento) {
        for (Ouvinte ouvinte : ouvintes) {
            ouvinte.avisandoAqui(evento);
        }
    }

}
