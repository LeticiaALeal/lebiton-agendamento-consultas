package com.project.lebiton.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.project.lebiton.dao.CadastrarDiasAgendaDaoInterface;
import com.project.lebiton.dao.factory.FactoryCadastroDiasAgendaDAO;
import com.project.lebiton.handleError.ErrorHandle;
import com.project.lebiton.model.impl.Agenda;
import com.project.lebiton.utils.CleanField;
import com.project.lebiton.utils.Message;
import com.project.lebiton.utils.RequestField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CadastroDiasAgendaController implements Initializable {

    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
    }

    @FXML
    TextField txDia, txHorario;
    @FXML
    Button btVoltar, btCadastrarDias;

    @FXML
    public void cadastrarDiasAgenda() throws Exception {
        ErrorHandle.checkFields(setFieldList());
        List<RequestField> lista = setFieldList();

        final CadastrarDiasAgendaDaoInterface dao = FactoryCadastroDiasAgendaDAO.criarCadastroDiasAgendadao();

        if (!dao.cadastrarDiasAgenda(this.setDiasAgenda())) {
            System.out.println("Ocorreu um erro!");

            Message.showAlert("ERRO AO CADASTRAR!", "Cadastro Inválido!",
                    "Não conseguimos processar seu cadastro! Tente novamente.", AlertType.ERROR);
        } else {
            System.out.println("Horário e Dia cadastrados!");

            Message.showAlert("CADASTRO REALIZADO!", "Horário e Dia cadastrados com sucesso!",
                    "Dia/Horário validados!", AlertType.INFORMATION);

            CleanField.cleanFieldList(lista);
        }

    }

    private Agenda setDiasAgenda() {
        return new Agenda.Builder()
                .horario(txHorario.getText())
                .dia(txDia.getText())
                .build();
    }

    private List<RequestField> setFieldList() {
        final List<RequestField> request = new ArrayList<>();
        final LinkedHashMap<String, TextField> map = new LinkedHashMap<>();

        map.put("horario", txHorario);
        map.put("dia", txDia);

        for (final String key : map.keySet()) {
            final RequestField field = new RequestField();
            field.setKey(key);
            field.setValue(map.get(key));

            request.add(field);
        }

        return request;
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
}