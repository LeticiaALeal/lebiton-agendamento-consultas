package com.project.lebiton.controller;

import com.project.lebiton.dao.PacienteDaoInterface;
import com.project.lebiton.dao.factory.FactoryPacienteDAO;
import com.project.lebiton.model.impl.Consulta;
import com.project.lebiton.model.impl.Sessao;
import com.project.lebiton.utils.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class PacienteController implements Initializable {

    @FXML
    private TableView<Consulta> tbConsultas = new TableView<>();
    @FXML
    private TableColumn<Consulta, Long> clCodigo = new TableColumn<>("Código");
    @FXML
    private TableColumn<Consulta, String> clEspecialidade = new TableColumn<>("Especialidade");
    @FXML
    private TableColumn<Consulta, String> clMedico = new TableColumn<>("Médico");
    @FXML
    private TableColumn<Consulta, String> clDia = new TableColumn<>("Dia");
    @FXML
    private TableColumn<Consulta, String> clHorario = new TableColumn<>("Horário");
    @FXML
    private Button btAgendar, btDeslogar;
    @FXML
    private TextField txUser, txEspecialista, txMedico, txDia, txHorario;

    FXMLLoader root = null;

    int index;
    Long idConsulta;

    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {
        txUser.setText(Sessao.getInstance().getEmail());
        initTable();
    }

    @FXML
    public void initTable() {
        try {
            clCodigo.setCellValueFactory(new PropertyValueFactory<>("id"));
            clMedico.setCellValueFactory(cellData -> cellData.getValue().getNomeMedico());
            clEspecialidade.setCellValueFactory(cellData -> cellData.getValue().getEspecialidade());
            clDia.setCellValueFactory(cellData -> cellData.getValue().getDia());
            clHorario.setCellValueFactory(cellData -> cellData.getValue().getHorario());

            tbConsultas.setItems(atualizaTabela());

        } catch (final Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public ObservableList<Consulta> atualizaTabela() {
        ObservableList<Consulta> lista = null;

        try {
            final PacienteDaoInterface dao = FactoryPacienteDAO.criarPacientendao();
            lista = FXCollections.observableArrayList(dao.listarAgenda(txUser.getText()));
        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return lista;
    }

    @FXML
    public void escolherEspecialidade(final ActionEvent event) {
        final Stage stage = (Stage) btAgendar.getScene().getWindow();
        try {
            final FXMLLoader root = new FXMLLoader(PacienteController.class.getResource("/com/project/lebiton/view/AgendaEspecialista.fxml"));
            final Scene scene = new Scene(root.load());
            stage.setScene(scene);
            stage.show();

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public Consulta tbPacienteExcluirClicked(final MouseEvent e) {
        index = tbConsultas.getSelectionModel().getSelectedIndex();
        final Consulta consultaSelecionada = tbConsultas.getItems().get(index);

        txEspecialista.setText(consultaSelecionada.getEspecialidade().get());
        txMedico.setText(consultaSelecionada.getNomeMedico().get());
        txDia.setText(consultaSelecionada.getDia().get());
        txHorario.setText(consultaSelecionada.getHorario().get());
        idConsulta = consultaSelecionada.getId();

        return consultaSelecionada;
    }

    @FXML
    public void excluirConsulta() {
        final PacienteDaoInterface daoInterface = FactoryPacienteDAO.criarPacientendao();
        final boolean remover = daoInterface.excluirConsultaAgendada(idConsulta);

        if (remover) {
            Message.showAlert("CONSULTA DESMARCADA COM SUCESSO!", "Consulta desmarcada!",
                    "Sua consulta foi desmarcada com sucesso!", AlertType.CONFIRMATION);

            tbConsultas.getItems().remove(index);
        }
    }

    @FXML
    public void deslogarOnClicked() {
        final Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("MENSAGEM");
        alert.setContentText("Deseja realmente sair?");

        final Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {

            final Stage stage = (Stage) btDeslogar.getScene().getWindow();

            try {
                root = new FXMLLoader(PacienteController.class.getResource("/com/project/lebiton/view/Login.fxml"));
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