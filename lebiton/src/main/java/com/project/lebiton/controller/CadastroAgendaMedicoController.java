package com.project.lebiton.controller;

import com.project.lebiton.dao.factory.FactoryAdministradorDAO;
import com.project.lebiton.dao.AdministradorDaoInterface;
import com.project.lebiton.handleError.ErrorHandle;
import com.project.lebiton.model.impl.*;
import com.project.lebiton.utils.CleanField;
import com.project.lebiton.utils.Message;
import com.project.lebiton.utils.RequestField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadastroAgendaMedicoController implements Initializable {

    @FXML
    private TableView<Medico> tbMedico = new TableView<>();
    @FXML
    private TableColumn<Medico, String> clMedico = new TableColumn<Medico, String>("Nome");
    @FXML
    private TableColumn<Medico, String> clEspecialidade = new TableColumn<Medico, String>("Especialidade");

    @FXML
    private TableView<Agenda> tbAgenda = new TableView<>();
    @FXML
    private TableColumn<Agenda, String> clDia = new TableColumn<Agenda, String>("Dia");
    @FXML
    private TableColumn<Agenda, String> clHorario = new TableColumn<Agenda, String>("Horário");

    @FXML
    private TextField txMedico, txHorario, txDia;
    @FXML
    private Button btVoltar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableMedico();
        initTableAgenda();

    }

    public void initTableMedico() {

        clMedico.setCellValueFactory(cellData -> cellData.getValue().getNome());
        clEspecialidade.setCellValueFactory(cellData -> cellData.getValue().getEspecialidade());

        tbMedico.setItems(atualizaTabelaMedico());
    }

    private ObservableList<Medico> atualizaTabelaMedico() {
        final AdministradorDaoInterface dao = FactoryAdministradorDAO.criarAdministradordao();
        return FXCollections.observableArrayList(dao.listarMedicos());
    }

    @FXML
    public Medico tbMedicoClicked(final MouseEvent e) {
        final int index = tbMedico.getSelectionModel().getSelectedIndex();
        final Medico medicoSelecionado = (Medico) tbMedico.getItems().get(index);

        txMedico.setText(medicoSelecionado.getNome().get());

        return medicoSelecionado;
    }

    public void initTableAgenda() {

        clDia.setCellValueFactory(cellData -> cellData.getValue().getDia());
        clHorario.setCellValueFactory(cellData -> cellData.getValue().getHorario());

        tbAgenda.setItems(atualizaTabelaAgenda());
    }

    private ObservableList<Agenda> atualizaTabelaAgenda() {
        ObservableList<Agenda> lista = null;
        try {
            final AdministradorDaoInterface dao = FactoryAdministradorDAO.criarAdministradordao();
            lista = FXCollections.observableArrayList(dao.listarAgenda());
        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;

    }

    @FXML
    public Agenda tbAgendaClicked(final MouseEvent e) {
        final int index = tbAgenda.getSelectionModel().getSelectedIndex();
        final Agenda agendaSelecionada = (Agenda) tbAgenda.getItems().get(index);

        txDia.setText(agendaSelecionada.getDia().get());
        txHorario.setText(agendaSelecionada.getHorario().get());

        return agendaSelecionada;
    }

    @FXML
    public void cadastrarAgendaMedico() throws Exception {
        ErrorHandle.checkFields(setFieldList());
        List<RequestField> lista = setFieldList();

        final AdministradorDaoInterface dao = FactoryAdministradorDAO.criarAdministradordao();

        if (!dao.createAgendaMedico(buildAgendaMedico())) {
            System.out.println("Ocorreu um erro!");

            Message.showAlert("ERRO AO CADASTRAR!", "Cadastro Inválido!",
                    "Não conseguimos processar seu cadastro! Tente novamente.", Alert.AlertType.ERROR);
        } else {
            System.out.println("Agenda do médico cadastrada!");

            Message.showAlert("CADASTRO REALIZADO!", "Agenda cadastrada com sucesso!",
                    "Dados validados!", Alert.AlertType.INFORMATION);

            CleanField.cleanFieldList(lista);
        }
    }

    private AgendaMedico buildAgendaMedico() {

        return new AgendaMedico.Builder()
                .medico(new Medico.Builder()
                        .nome(txMedico.getText())
                        .build())
                .agenda(new Agenda.Builder()
                        .horario(txHorario.getText())
                        .dia(txDia.getText())
                        .build())
                .build();
    }

    private List<RequestField> setFieldList() {
        final List<RequestField> request = new ArrayList<>();
        final LinkedHashMap<String, TextField> map = new LinkedHashMap<>();

        map.put("medico", txMedico);
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