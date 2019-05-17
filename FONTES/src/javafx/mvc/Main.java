/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.mvc;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.mvc.controller.ConfigBancoController;
import javafx.mvc.controller.LoginController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.mvc.controller.PrincipalController;
import javafx.mvc.services.UsuarioLogado;
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

        File config = new File(System.getProperty("user.dir").concat("/db.properties"));
        if (!config.exists()) {
            // Abre a tela validar o login
            Stage cfStage = new Stage();
            FXMLLoader loaderConfig = new FXMLLoader();
            loaderConfig.setLocation(LoginController.class.getResource("/javafx/mvc/view/ConfigBanco.fxml"));
            AnchorPane pageConfig = (AnchorPane) loaderConfig.load();
            cfStage.setTitle("Config");
            Scene sceneConfig = new Scene(pageConfig);
            cfStage.setScene(sceneConfig);

            ConfigBancoController cConfig = loaderConfig.getController();
            cConfig.setDialogStage(cfStage);
            
            cfStage.initOwner(primaryStage);
           //cfStage.setMaximized(true);
           //cfStage.setFullScreen(true);
            cfStage.initModality(Modality.APPLICATION_MODAL);
            cfStage.showAndWait();
            
            if (!cConfig.isConfigurado()) {
                primaryStage.close();
                Platform.exit();
                System.exit(0);
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
            System.out.println(UsuarioLogado.getInstance().getUser().getNomeUsuario());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
