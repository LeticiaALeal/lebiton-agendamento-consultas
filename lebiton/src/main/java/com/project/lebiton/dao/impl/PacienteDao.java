package com.project.lebiton.dao.impl;

import com.project.lebiton.dao.PacienteDaoInterface;
import com.project.lebiton.dao.connction.ConnectionFactory;
import com.project.lebiton.model.impl.Agenda;
import com.project.lebiton.model.impl.Consulta;
import com.project.lebiton.model.impl.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao implements PacienteDaoInterface {

    private Connection connection;

    public boolean cadastrarConsulta(final Consulta consulta) {
        try {
            connection = ConnectionFactory.getConnection();
            final PreparedStatement statement;

            statement = connection.prepareStatement("call createConsulta(?,?,?,?)");
            statement.setString(1, consulta.getNomeMedico().get());
            statement.setString(2, consulta.getEmailPaciente());
            statement.setString(3, consulta.getHorario().get());
            statement.setString(4, consulta.getDia().get());

            if (!statement.execute()) {
                return true;
            }

            ConnectionFactory.closeConnection(connection, statement);

        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return false;
    }

    public List<Consulta> listarAgenda(final String email) {
        final List<Consulta> consultas = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select c.id, m.especialidade, u.nome, dia, horario " +
                    "from consulta c " +
                    "inner join usuario u on c.idMedico = u.id " +
                    "inner join medicos m on c.idMedico = m.idUsuario where emailPaciente = ? ");
            statement.setString(1, email);

            result = statement.executeQuery();

            while (result.next()) {
                final Consulta cs = new Consulta();
                cs.setId(result.getLong("id"));

                final Medico medico = new Medico();
                medico.setEspecialidade(result.getString("especialidade"));
                medico.setNome(result.getString("nome"));
                cs.setMedico(medico);

                final Agenda agenda = new Agenda();
                agenda.setDia(result.getString("dia"));
                agenda.setHorario(result.getString("horario"));
                cs.setAgenda(agenda);

                consultas.add(cs);
            }

        } catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return consultas;
    }

    public boolean excluirConsultaAgendada(final Long id) {
        try {
            connection = ConnectionFactory.getConnection();
            final PreparedStatement statement;

            statement = connection.prepareStatement("delete from consulta where id = ?");
            statement.setLong(1, id);

            statement.executeUpdate();
            return true;

        } catch (final Exception e) {
            System.out.println("Erro ao excluir consulta: " + e.getMessage());
        }

        return false;
    }
}