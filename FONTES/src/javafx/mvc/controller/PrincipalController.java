package javafx.mvc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.UsuarioLogado;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lukas
 */
public class PrincipalController implements Initializable {

    private ArrayList<Scene> sceneList;

    private Stage stagePrincipal;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private MenuItem menuItemCadastroCliente;

    @FXML
    private MenuItem menuItemSistemaSair;

    @FXML
    private Label lbUsuario;

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

    }

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    public void setLabelUsuario() {
        this.lbUsuario.setText("Usuario: " + UsuarioLogado.getInstance().getUser().getNomeUsuario());
    }

    private ArrayList<Scene> loadScenes() throws IOException {
        ArrayList<Scene> lista = new ArrayList<>();

        ArrayList<String> listaNomes = new ArrayList<>();

        listaNomes.add("Caixa");
        listaNomes.add("FechamentoCaixa");
        listaNomes.add("Pedido");
        
        UsuarioModel user = UsuarioLogado.getInstance().getUser();
        
        if () {
            
        }
        listaNomes.add("Usuario");
        listaNomes.add("Produto");
        listaNomes.add("Cliente");
        
        

        for (String str : listaNomes) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PrincipalController.class.getResource("/javafx/mvc/view/" + str + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            lista.add(scene);
        }

        return lista;
    }

}
