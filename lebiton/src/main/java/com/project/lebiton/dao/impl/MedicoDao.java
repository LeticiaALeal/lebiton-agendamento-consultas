package com.project.lebiton.dao.impl;

import com.project.lebiton.dao.MedicoDaoInterface;
import com.project.lebiton.dao.connction.ConnectionFactory;
import com.project.lebiton.model.impl.Agenda;
import com.project.lebiton.model.impl.Consulta;
import com.project.lebiton.model.impl.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicoDao implements MedicoDaoInterface {
    private Connection connection;

    public List<Agenda> listarHorarios(final String email) {
        final List<Agenda> agenda = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select dia, horario from horarios_atendimento_medico where email = ?");
            statement.setString(1, email);

            result = statement.executeQuery();

            while (result.next()) {
                final Agenda ag = new Agenda();
                ag.setDia(result.getString("dia"));
                ag.setHorario(result.getString("horario"));
                agenda.add(ag);
            }
        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return agenda;
    }

    @Override
    public List<Consulta> listarConsultas(final String email) {
        final List<Consulta> consulta = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select c.id, u.nome, horario, dia " +
                    "from consulta c " +
                    "inner join usuario u on c.idPaciente = u.id " +
                    "where emailMedico = ? ");
            statement.setString(1, email);

            result = statement.executeQuery();

            while (result.next()) {
                final Consulta cs = new Consulta();
                cs.setId((result.getLong("id")));

                final Paciente paciente = new Paciente();
                paciente.setNome(result.getString("nome"));
                cs.setPaciente(paciente);

                final Agenda agenda = new Agenda();
                agenda.setDia(result.getString("dia"));
                agenda.setHorario(result.getString("horario"));
                cs.setAgenda(agenda);

                consulta.add(cs);
            }
        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return consulta;
    }
}