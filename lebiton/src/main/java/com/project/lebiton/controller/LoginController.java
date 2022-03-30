package com.project.lebiton.controller;

import com.project.lebiton.exceptions.ImpossivelEfetuarLoginException;
import com.project.lebiton.facade.LoginFacade;
import com.project.lebiton.factory.UsuarioFactory;
import com.project.lebiton.handleError.ErrorHandle;
import com.project.lebiton.handleError.MarkFieldError;
import com.project.lebiton.model.UsuarioInterface;
import com.project.lebiton.model.impl.Administrador;
import com.project.lebiton.model.impl.Medico;
import com.project.lebiton.model.impl.Paciente;
import com.project.lebiton.model.impl.Sessao;
import com.project.lebiton.utils.Message;
import com.project.lebiton.utils.RequestField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LoginController implements Initializable {

    @FXML
    public TextField txLogin, txSenha;
    @FXML
    public Button btLogar, btSair, btCadastrar;

    final String VIEW_PATH = "/com/project/lebiton/view/";

    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
    }

    @FXML
    public void logar(final ActionEvent actionEvent) throws Exception {

        ErrorHandle.checkFields(setFieldList());

        final LoginFacade facade = new LoginFacade();
        final UsuarioInterface user = UsuarioFactory.criar(txLogin.getText(), txSenha.getText());

        if (!facade.logar(user)) {
            System.out.println("Usuário/senha inválido!");

            MarkFieldError.markInvalidField(Arrays.asList(txLogin, txSenha));

            Message.showAlert("ERRO AO LOGAR!", "Login Inválido!",
                    "Usuário/Senha inválidos! Tente novamente.", Alert.AlertType.ERROR);

            throw new ImpossivelEfetuarLoginException("Login ou senha invalido");
        }

        this.criarTelaParaUsuario(user);
    }

    @FXML
    public void cadastrarPaciente(final ActionEvent actionEvent) {
        final Stage stage = (Stage) btCadastrar.getScene().getWindow();

        try {
            final FXMLLoader root = new FXMLLoader(LoginController.class.getResource(VIEW_PATH.concat("CadastroPaciente.fxml")));
            this.abrirTela(stage, root, "Cadastrar Paciente");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sairSistema(final ActionEvent actionEvent) {
        Platform.exit();
    }

    private void criarTelaParaUsuario(final UsuarioInterface user) throws IOException {
        final Stage stage = (Stage) btLogar.getScene().getWindow();
        Sessao.getInstance().setEmail(txLogin.getText());
        final FXMLLoader root;

        if (user instanceof Administrador) {
            root = new FXMLLoader(LoginController.class.getResource(VIEW_PATH.concat("HomeAdm.fxml")));
            this.abrirTela(stage, root, "Tela do Administrador");

        } else if (user instanceof Paciente) {
            root = new FXMLLoader(LoginController.class.getResource(VIEW_PATH.concat("Paciente.fxml")));
            this.abrirTela(stage, root, "Tela do Paciente");

        } else if (user instanceof Medico) {
            root = new FXMLLoader(LoginController.class.getResource(VIEW_PATH.concat("Medico.fxml")));
            this.abrirTela(stage, root, "Tela do Medico");
        }

        System.out.println("Login efetuado com sucesso");

    }

    private void abrirTela(final Stage stage, final FXMLLoader root, final String page) throws IOException {
        final Scene scene = new Scene(root.load());
        stage.setScene(scene);
        stage.setTitle(page);
        stage.show();
    }

    private List<RequestField> setFieldList() {
        final List<RequestField> request = new ArrayList<>();
        final LinkedHashMap<String, TextField> map = new LinkedHashMap<>();

        map.put("login", txLogin);
        map.put("senha", txSenha);

        for (final String key : map.keySet()) {
            final RequestField field = new RequestField();
            field.setKey(key);
            field.setValue(map.get(key));

            request.add(field);
        }

        return request;
    }
}