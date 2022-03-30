package com.project.lebiton.controller;

import com.project.lebiton.dao.AdministradorDaoInterface;
import com.project.lebiton.dao.factory.FactoryAdministradorDAO;
import com.project.lebiton.model.impl.AgendaMedico;
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

public class ExibirAgendasController implements Initializable {

    @FXML
    private TableView<AgendaMedico> tbAgendaMedico = new TableView<>();
    @FXML
    private TableColumn<AgendaMedico, String> clMedico = new TableColumn<>("Nome");
    @FXML
    private TableColumn<AgendaMedico, String> clEspecialidade = new TableColumn<>("Especialidade");
    @FXML
    private TableColumn<AgendaMedico, String> clDia = new TableColumn<>("Dia");
    @FXML
    private TableColumn<AgendaMedico, String> clHorario = new TableColumn<>("Horario");
    @FXML
    private Button btVoltar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    public void initTable() {

        clMedico.setCellValueFactory(cellData -> cellData.getValue().getMedico().getNome());
        clEspecialidade.setCellValueFactory(cellData -> cellData.getValue().getEspecialidade());
        clDia.setCellValueFactory(cellData -> cellData.getValue().getDia());
        clHorario.setCellValueFactory(cellData -> cellData.getValue().getHorario());

        tbAgendaMedico.setItems(atualizaTabela());
    }

    public ObservableList<AgendaMedico> atualizaTabela() {
        final AdministradorDaoInterface dao = FactoryAdministradorDAO.criarAdministradordao();
        return FXCollections.observableArrayList(dao.listarAgendaMedico());
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