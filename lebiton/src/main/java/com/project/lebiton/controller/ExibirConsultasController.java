package com.project.lebiton.controller;

import com.project.lebiton.dao.AdministradorDaoInterface;
import com.project.lebiton.dao.factory.FactoryAdministradorDAO;
import com.project.lebiton.model.impl.Consulta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ExibirConsultasController implements Initializable {

    @FXML
    private TableView<Consulta> tbConsultas = new TableView<>();
    @FXML
    private TableColumn<Consulta, String> clMedico = new TableColumn<>("Medico");
    @FXML
    private TableColumn<Consulta, String> clPaciente = new TableColumn<>("Paciente");
    @FXML
    private TableColumn<Consulta, String> clHorario = new TableColumn<>("Horario");
    @FXML
    private TableColumn<Consulta, String> clDia = new TableColumn<>("Dia");
    @FXML
    private Button btVoltar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableConsultas();
    }

    public void initTableConsultas() {

        clMedico.setCellValueFactory(cellData -> cellData.getValue().getNomeMedico());
        clPaciente.setCellValueFactory(cellData -> cellData.getValue().getNomePaciente());
        clHorario.setCellValueFactory(cellData -> cellData.getValue().getHorario());
        clDia.setCellValueFactory(cellData -> cellData.getValue().getDia());

        tbConsultas.setItems(atualizaTabelaConsultas());
    }

    private ObservableList<Consulta> atualizaTabelaConsultas() {
        final AdministradorDaoInterface dao = FactoryAdministradorDAO.criarAdministradordao();
        return FXCollections.observableArrayList(dao.listarConsultas());
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