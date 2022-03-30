package com.project.lebiton.controller;

import com.project.lebiton.dao.MedicoDaoInterface;
import com.project.lebiton.dao.factory.FactoryMedicoDAO;
import com.project.lebiton.model.impl.Consulta;
import com.project.lebiton.model.impl.Sessao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MedicoConsultasController implements Initializable {

    @FXML
    private TableView<Consulta> tbConsultas = new TableView();
    @FXML
    private TableColumn<Consulta, Long> clCodigo = new TableColumn<>("Código");
    @FXML
    private TableColumn<Consulta, String> clPaciente = new TableColumn<>("Paciente");
    @FXML
    private TableColumn<Consulta, String> clDia = new TableColumn<>("Dia");
    @FXML
    private TableColumn<Consulta, String> clHorario = new TableColumn<>("Horário");
    @FXML
    private TextField txUser;
    @FXML
    private Button btVoltar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txUser.setText(Sessao.getInstance().getEmail());
        initTable();
    }

    @FXML
    public void initTable() {
        try {
            clCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
            clPaciente.setCellValueFactory(cellData -> cellData.getValue().getNomePaciente());
            clDia.setCellValueFactory(cellData -> cellData.getValue().getDia());
            clHorario.setCellValueFactory(cellData -> cellData.getValue().getHorario());

            tbConsultas.setItems(atualizaTabela());

        } catch (final Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

    }

    private ObservableList<Consulta> atualizaTabela() {
        ObservableList<Consulta> lista = null;

        try {
            final MedicoDaoInterface dao = FactoryMedicoDAO.criarMedicodao();
            lista = FXCollections.observableArrayList(dao.listarConsultas(txUser.getText()));
        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    @FXML
    public void voltarMedico() {
        final Stage stage = (Stage) btVoltar.getScene().getWindow();
        try {

            final FXMLLoader root = new FXMLLoader(MedicoController.class.getResource("/com/project/lebiton/view/Medico.fxml"));
            final Scene scene = new Scene(root.load());
            stage.setScene(scene);
            stage.show();

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}