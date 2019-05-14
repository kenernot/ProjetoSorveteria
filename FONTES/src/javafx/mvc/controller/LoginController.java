package javafx.mvc.controller;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.mvc.dao.UsuarioDao;
import javafx.mvc.model.UsuarioModel;
import javafx.mvc.services.Conexao;
import javafx.mvc.services.HashSHA2;
import javafx.mvc.services.UsuarioLogado;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alisson H. Silva
 */
public class LoginController implements Initializable {

    @FXML
    private Button btEntrar;

    @FXML
    private Button btSair;

    @FXML
    private Label lbErro;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;

    @FXML
    void btEntrarClick(ActionEvent event) throws Exception {
        //Código para Validar o Login

        if (isValidLogin()) {
            isAllowed = true;
            dialogStage.close();
        } else {

            Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Date now = new Date();
            String dataFinal = formatter.format(now);

            this.lbErro.setText(dataFinal + " > Usuário e/ou senha inválidos!");
        }
    }

    @FXML
    void btSairClick(ActionEvent event) {
        dialogStage.close();
    }

    private Stage dialogStage;
    private boolean isAllowed = false;

    /**
     * Initializes the controller class.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isIsAllowed() {
        return isAllowed;
        // TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private Boolean isValidLogin() throws Exception {
        Boolean isValid = false;

        UsuarioModel digitedUser = new UsuarioModel();
        digitedUser.setNomeUsuario(txtUsuario.getText().toLowerCase());
        digitedUser.setSenhaUsuario(txtSenha.getText());

        UsuarioDao daoUsuario = new UsuarioDao(Conexao.getInstance().getConn());

        String c = " SHA2(nomeUsuario,'256')='" + HashSHA2.hashSHA2(digitedUser.getNomeUsuario()) + "' AND senhaUsuario='" + HashSHA2.hashSHA2(digitedUser.getSenhaUsuario()) + "' and status='A' limit 1;";

        List<UsuarioModel> resultadosDao = (List<UsuarioModel>) daoUsuario.buscar(c);

        if (!resultadosDao.isEmpty()) {
            isValid = true;
            digitedUser = resultadosDao.get(0);
            digitedUser.setSenhaUsuario("");
            UsuarioLogado.getInstance().setUser(digitedUser);
            
        }

        return isValid;
    }

}