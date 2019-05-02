package javafx.mvc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lukas
 */
public class PrincipalController implements Initializable {

    private Stage stagePrincipal;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuItem menuItemCadastroCliente;

    @FXML
    private MenuItem menuItemSistemaSair;

    @FXML
    public void menuItemSistemaSairClicked() {
        stagePrincipal.close();
    }

    @FXML
    public void menuItemCadastroClienteClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(VIEWMODELOCONTROLLER.class.getResource("/javafx/mvc/view/VIEWMODELO.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        //PrincipalController c = loader.getController();
        //c.setStagePrincipal(primaryStage);

        anchorPane.getChildren().add(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

}
