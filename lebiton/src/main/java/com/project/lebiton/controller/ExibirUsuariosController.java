package com.project.lebiton.controller;

import com.project.lebiton.dao.AdministradorDaoInterface;
import com.project.lebiton.dao.factory.FactoryAdministradorDAO;
import com.project.lebiton.model.impl.Medico;
import com.project.lebiton.model.impl.Paciente;
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


public class ExibirUsuariosController implements Initializable {

    @FXML
    private TableView<Medico> tbMedicos = new TableView<>();
    @FXML
    private TableColumn<Medico, String> clMedico = new TableColumn<Medico, String>("Nome");
    @FXML
    private TableColumn<Medico, String> clEspecialidade = new TableColumn<Medico, String>("Especialidade");
    @FXML
    private TableColumn<Medico, String> clCrm = new TableColumn<Medico, String>("CRM");

    @FXML
    private TableView<Paciente> tbPacientes = new TableView<>();
    @FXML
    private TableColumn<Paciente, String> clPaciente = new TableColumn<>("Nome");

    @FXML
    private Button btVoltar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableMedico();
        initTablePaciente();
    }

    public void initTableMedico() {

        clMedico.setCellValueFactory(cellData -> cellData.getValue().getNome());
        clEspecialidade.setCellValueFactory(cellData -> cellData.getValue().getEspecialidade());
        clCrm.setCellValueFactory(cellData -> cellData.getValue().getCrm());

        tbMedicos.setItems(atualizaTabelaMedico());
    }

    private ObservableList<Medico> atualizaTabelaMedico() {
        final AdministradorDaoInterface dao = FactoryAdministradorDAO.criarAdministradordao();
        return FXCollections.observableArrayList(dao.listarMedicos());
    }

    public void initTablePaciente() {

        clPaciente.setCellValueFactory(cellData -> cellData.getValue().getNome());

        tbPacientes.setItems(atualizaTabelaPaciente());
    }

    private ObservableList<Paciente> atualizaTabelaPaciente() {
        final AdministradorDaoInterface dao = FactoryAdministradorDAO.criarAdministradordao();
        return FXCollections.observableArrayList(dao.listarPacientes());
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