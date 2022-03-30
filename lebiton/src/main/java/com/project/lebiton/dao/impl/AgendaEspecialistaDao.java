package com.project.lebiton.dao.impl;

import com.project.lebiton.dao.AgendarEspecialistaDaoInterface;
import com.project.lebiton.dao.connction.ConnectionFactory;
import com.project.lebiton.model.impl.Agenda;
import com.project.lebiton.model.impl.AgendaMedico;
import com.project.lebiton.model.impl.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AgendaEspecialistaDao implements AgendarEspecialistaDaoInterface {

    private Connection connection;

    public List<AgendaMedico> listar() {
        final List<AgendaMedico> consultasMedico = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("SELECT * from view_agenda_medico");
            result = statement.executeQuery();

            while (result.next()) {
                final AgendaMedico ag = new AgendaMedico();

                final Medico medico = new Medico();
                medico.setNome(result.getString("nome"));
                medico.setEspecialidade(result.getString("especialidade"));
                ag.setMedico(medico);

                final Agenda agenda = new Agenda();
                agenda.setDia(result.getString("dia"));
                agenda.setHorario(result.getString("horario"));
                ag.setAgenda(agenda);

                consultasMedico.add(ag);
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return consultasMedico;
    }

    public List<AgendaMedico> listaFiltrada(final String especialidade) {
        final List<AgendaMedico> consultasMedico = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select * from view_agenda_medico where especialidade = ?");
            statement.setString(1, especialidade);

            result = statement.executeQuery();

            while (result.next()) {
                final AgendaMedico ag = new AgendaMedico();

                final Medico medico = new Medico();
                medico.setNome(result.getString("nome"));
                medico.setEspecialidade(result.getString("especialidade"));
                ag.setMedico(medico);

                final Agenda agenda = new Agenda();
                agenda.setDia(result.getString("dia"));
                agenda.setHorario(result.getString("horario"));
                ag.setAgenda(agenda);

                consultasMedico.add(ag);
            }
        } catch (final Exception e) {
            System.out.println("########: " + e.getMessage());
            e.printStackTrace();
        }

        return consultasMedico;
    }

}
