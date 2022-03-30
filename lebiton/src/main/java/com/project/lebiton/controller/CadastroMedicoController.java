package com.project.lebiton.controller;

import com.project.lebiton.exceptions.CadastroInvalidoException;
import com.project.lebiton.handleError.ErrorHandle;
import com.project.lebiton.model.impl.Medico;
import com.project.lebiton.utils.CleanField;
import com.project.lebiton.utils.Message;
import com.project.lebiton.utils.RequestField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CadastroMedicoController implements Initializable {

    @FXML
    TextField txNome, txCrm, txEspecialidade, txTelefone, txEmail;
    @FXML
    PasswordField txSenha;
    @FXML
    Button btCadastrar, btVoltar;

    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
    }

    @FXML
    public void cadastrar() throws Exception {
        List<RequestField> lista = setFieldList();

        ErrorHandle.checkFields(lista);

        Medico medico = setMedicoBuider();

        if (!medico.createUser(medico)) {
            System.out.println("Ocorreu um erro!");

            Message.showAlert("ERRO AO CADASTRAR!", "Cadastro Inválido!",
                    "Não conseguimos processar seu cadastro! Tente novamente.", AlertType.ERROR);

            throw new CadastroInvalidoException("Não foi possivel cadastrar medico");

        } else {
            System.out.println("Paciente cadastrado!");

            Message.showAlert("CADASTRO REALIZADO!", "Médico cadastrado com sucesso!",
                    "Usuário/Senha validados! Volte para a tela inicial e prossiga.", AlertType.INFORMATION);

            CleanField.cleanFieldList(lista);
        }
    }

    @FXML
    public void voltarHome() {
        final Stage stage = (Stage) btVoltar.getScene().getWindow();
        try {

            final FXMLLoader root = new FXMLLoader(CadastroMedicoController.class.getResource("/com/project/lebiton/view/HomeAdm.fxml"));
            final Scene scene = new Scene(root.load());
            stage.setScene(scene);
            stage.setTitle("Tela do Administrador");
            stage.show();

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private Medico setMedicoBuider() {
        return new Medico.Builder()
                .nome(txNome.getText())
                .email(txEmail.getText())
                .telefone(txTelefone.getText())
                .senha(txSenha.getText())
                .especialidade(txEspecialidade.getText())
                .crm(txCrm.getText())
                .build();
    }

    private List<RequestField> setFieldList() {
        final List<RequestField> request = new ArrayList<>();
        final LinkedHashMap<String, TextField> map = new LinkedHashMap<>();

        map.put("nome", txNome);
        map.put("crm", txCrm);
        map.put("telefone", txTelefone);
        map.put("especialidade", txEspecialidade);
        map.put("email", txEmail);
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