package com.project.lebiton.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.project.lebiton.dao.MedicoDaoInterface;
import com.project.lebiton.dao.factory.FactoryMedicoDAO;
import com.project.lebiton.model.impl.Agenda;
import com.project.lebiton.model.impl.Sessao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MedicoController implements Initializable {

    @FXML
    private TableView<Agenda> tbHorarios = new TableView<>();
    @FXML
    private TableColumn<Agenda, String> clDia = new TableColumn<Agenda, String>("Dia");
    @FXML
    private TableColumn<Agenda, String> clHorario = new TableColumn<Agenda, String>("Horario");
    @FXML
    private TextField txUser;
    @FXML
    private Button btAgenda, btDeslogar;

    FXMLLoader root = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txUser.setText(Sessao.getInstance().getEmail());
        initTable();
    }

    public void initTable() {
        clDia.setCellValueFactory(cellData -> cellData.getValue().getDia());
        clHorario.setCellValueFactory(cellData -> cellData.getValue().getHorario());

        tbHorarios.setItems(atualizaTabela());
    }

    private ObservableList<Agenda> atualizaTabela() {
        ObservableList<Agenda> lista = null;
        try {
            final MedicoDaoInterface dao = FactoryMedicoDAO.criarMedicodao();
            lista = FXCollections.observableArrayList(dao.listarHorarios(txUser.getText()));
        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    @FXML
    public void visualizarConsultas(final ActionEvent event) {
        final Stage stage = (Stage) btAgenda.getScene().getWindow();
        try {
            final FXMLLoader root = new FXMLLoader(MedicoConsultasController.class.getResource("/com/project/lebiton/view/MedicoConsultas.fxml"));
            final Scene scene = new Scene(root.load());
            stage.setScene(scene);
            stage.setTitle("Tela do Médico");
            stage.show();

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deslogarOnClicked() {
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("MENSAGEM");
        alert.setContentText("Deseja realmente sair?");

        final Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {

                final Stage stage = (Stage) btDeslogar.getScene().getWindow();

                try {
                    root = new FXMLLoader(MedicoController.class.getResource("/com/project/lebiton/view/Login.fxml"));
                    final Scene scene = new Scene(root.load());
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.show();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}