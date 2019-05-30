package javafx.mvc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.mvc.dao.Teste;
import javafx.mvc.services.Conexao;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InitialConfigController {

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btProsseguir;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btTestar;

    @FXML
    private Tab tabDbConn;

    @FXML
    private Tab tabDbProperties;

    @FXML
    private Label lbTeste;

    @FXML
    private TabPane tabPane;

    @FXML
    private TextField txtConnurl;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUser;

    private boolean finished = false;

    public boolean isFinished() {
        return finished;
    }

    @FXML
    void btProsseguirClick(ActionEvent event) {
        this.finished = true;
        dialogStage.close();
    }

    @FXML
    void btSalvarClick(ActionEvent event) {
        File file = new File("db.properties");
        Properties properties = new Properties();
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                properties.load(fis);
            } catch (IOException e) {
                System.out.println("Pau: " + e.getMessage());
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream("db.properties");
            properties.setProperty("user", txtUser.getText());
            properties.setProperty("password", txtPassword.getText());
            String text = txtConnurl.getText();
            //text = text.replace("\\", "\\");
            properties.setProperty("connurl", text);
            properties.setProperty("conndriver", "com.mysql.jdbc.Driver");
            properties.store(fos, "Configurações");
            fos.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        tabSelected(1);

    }

    @FXML
    void btTestarClick(ActionEvent event) throws InterruptedException {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream(System.getProperty("user.dir").concat("/db.properties")));
        } catch (IOException ex) {
            System.out.println("teste");
        }

        Connection conn = null;
        try {
            Class.forName(config.getProperty("conndriver"));

            conn = DriverManager.getConnection(
                    config.getProperty("connurl"),
                    config
            );

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {

            Teste teste = new Teste(conn);
            if (teste.testar()) {
                lbTeste.setText("OK");
                lbTeste.setStyle("color: green");
            } else {
                lbTeste.setText("FALHA");
                lbTeste.setStyle("color: red");
                Thread.sleep(2000);
                tabSelected(0);
            }
        } catch (Exception ex) {
            lbTeste.setText("FALHA");
            lbTeste.setStyle("color: red");
            Thread.sleep(2000);
            tabSelected(0);
        }

    }

    @FXML
    void initialize() {

    }

    private void tabSelected(int id) {
        SingleSelectionModel<Tab> tabModel = tabPane.getSelectionModel();
        tabModel.select(id);
    }
}
