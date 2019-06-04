package javafx.mvc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.mvc.Main;
import javafx.mvc.contracts.Ouvinte;
import javafx.mvc.events.EventoOcorrido;
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
public class PrincipalController implements Initializable, Ouvinte {

    private Map<String, Scene> sceneList;

    private Stage stagePrincipal;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem menuItemFinanceiroAberturaCaixa;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label lbUsuario;

    @FXML
    private MenuItem menuItemCadastroCliente;

    @FXML
    private MenuItem menuItemCadastroProduto;

    @FXML
    private MenuItem menuItemCadastroUsuario;

    @FXML
    private MenuItem menuItemFinanceiroFecharCaixa;

    @FXML
    private MenuItem menuItemFinanceiroItemCaixa;

    @FXML
    private MenuItem menuItemFinanceiroRelatorioCaixa;

    @FXML
    private MenuItem menuItemPedidoAbrir;

    @FXML
    private MenuItem menuItemSistemaSair;

    @FXML
    public void menuItemSistemaSairClicked() {
        stagePrincipal.close();
    }

    @FXML
    public void menuItemCadastroClienteClick() throws IOException {
        /*
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(VIEWMODELOCONTROLLER.class.getResource("/javafx/mvc/view/VIEWMODELO.fxml"));
         Parent root = loader.load();
         Scene scene = new Scene(root);

         //PrincipalController c = loader.getController();
         //c.setStagePrincipal(primaryStage);
         anchorPane.getChildren().add(root);
         */
        chamaTela("Cliente");
    }

    @FXML
    void menuItemCadastroProdutoClick() {
        chamaTela("Produto");
    }

    @FXML
    void menuItemCadastroUsuarioClick() {
        chamaTela("Usuario");
    }

    @FXML
    void menuItemFinanceiroFecharCaixaClick() {
        chamaTela("FechamentoCaixa");
    }

    @FXML
    void menuItemFinanceiroItemCaixaClick() {
        chamaTela("ItemCaixa");
    }

    @FXML
    void menuItemFinanceiroRelatorioCaixaClick() {
        chamaTela("Caixa");
    }

    @FXML
    void menuItemPedidoAbrirClick() {
        chamaTela("Pedido");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Main.subscribe(this);
    }

    public Stage getStagePrincipal() {
        return stagePrincipal;
    }

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    @FXML
    void menuItemFinanceiroAberturaCaixaClick() {
        chamaTela("AberturaCaixa");
    }

    public void setLabelUsuario() {
        this.lbUsuario.setText("Usuario: " + UsuarioLogado.getInstance().getUser().getNomeUsuario());
    }

    private Map<String, Scene> loadScenes() throws IOException {
        Map<String, Scene> lista = new HashMap<>();

        ArrayList<String> listaNomes = new ArrayList<>();

        listaNomes.add("Caixa");
        listaNomes.add("FechamentoCaixa");
        listaNomes.add("AberturaCaixa");
        listaNomes.add("ItemCaixa");
        listaNomes.add("Pedido");

        // Burger R$10
        UsuarioModel user = UsuarioLogado.getInstance().getUser();

        this.menuItemCadastroCliente.setVisible(false);
        this.menuItemCadastroUsuario.setVisible(false);
        this.menuItemCadastroProduto.setVisible(false);

        //System.out.println(user.getUsuario());
        if (user.getUsuario().equals("1")) {
            listaNomes.add("Usuario");
            this.menuItemCadastroUsuario.setVisible(true);
        }
        if (user.getProduto().equals("1")) {
            listaNomes.add("Produto");
            this.menuItemCadastroProduto.setVisible(true);
        }
        if (user.getCliente().equals("1")) {
            listaNomes.add("Cliente");
            this.menuItemCadastroCliente.setVisible(true);
        }

        for (String str : listaNomes) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PrincipalController.class.getResource("/javafx/mvc/view/" + str + ".fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            lista.put(str, scene);
        }

        return lista;
    }

    public void setScenes() {
        try {
            this.sceneList = loadScenes();
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public void chamaTela(String nomeTela) {
        Scene get = this.sceneList.get(nomeTela);

        if (anchorPane.getChildren().indexOf(get.getRoot()) != -1) {
            fecharTela(nomeTela);
        }
        this.anchorPane.getChildren().add(get.getRoot());

    }

    @Override
    public void avisandoAqui(EventoOcorrido evento) {
        if (evento.getNome().equals("FECHAR_TELA")) {
            fecharTela((String) evento.getDados());
        }
    }

    private void fecharTela(String nomeTela) {
        Scene get = this.sceneList.get(nomeTela);
        anchorPane.getChildren().remove(anchorPane.getChildren().indexOf(get.getRoot()));
    }

}
