package com.project.lebiton.dao.impl;

import com.project.lebiton.dao.AdministradorDaoInterface;
import com.project.lebiton.dao.connction.ConnectionFactory;
import com.project.lebiton.model.impl.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDao implements AdministradorDaoInterface {
    private Connection connection;

    @Override
    public List<Medico> listarMedicos() {
        final List<Medico> medico = new ArrayList<Medico>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select u.nome, especialidade, crm\n" +
                    "from medicos m\n" +
                    "inner join usuario u on u.id = m.idUsuario " );
            result = statement.executeQuery();

            while (result.next()) {
                final Medico md = new Medico();
                md.setNome(result.getString("nome"));
                md.setEspecialidade(result.getString("especialidade"));
                md.setCrm(result.getString("crm"));
                medico.add(md);
            }
        }catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return medico;
    }

    @Override
    public List<Agenda> listarAgenda() {
        final List<Agenda> agenda = new ArrayList<Agenda>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select dia, horario from agenda ");
            result = statement.executeQuery();

            while (result.next()) {
                final Agenda ag = new Agenda();
                ag.setDia(result.getString("dia"));
                ag.setHorario(result.getString("horario"));
                agenda.add(ag);
            }
        }catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return agenda;
    }

    @Override
    public List<AgendaMedico> listarAgendaMedico() {
        final List<AgendaMedico> agendaMedico = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select * from view_agenda_medico" );
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

                agendaMedico.add(ag);
            }
        }catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return agendaMedico;
    }

    @Override
    public List<Paciente> listarPacientes() {
        final List<Paciente> paciente = new ArrayList<Paciente>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select u.nome from pacientes p\n" +
                    "inner join usuario u on p.idUsuario = u.id" );
            result = statement.executeQuery();

            while (result.next()) {
                final Paciente p = new Paciente();
                p.setNome(result.getString("nome"));
                paciente.add(p);
            }
        }catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return paciente;
    }

    @Override
    public List<Consulta> listarConsultas() {
        final List<Consulta> consultas = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("select * from view_consultas_adm");
            result = statement.executeQuery();

            while (result.next()) {
                final Consulta cs = new Consulta();

                final Medico medico = new Medico();
                medico.setNome(result.getString("medico"));
                cs.setMedico(medico);

                final Paciente paciente = new Paciente();
                paciente.setNome(result.getString("paciente"));
                cs.setPaciente(paciente);

                final Agenda agenda = new Agenda();
                agenda.setDia(result.getString("dia"));
                agenda.setHorario(result.getString("horario"));
                cs.setAgenda(agenda);

                consultas.add(cs);
            }
        }catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return consultas;
    }

    @Override
    public boolean createAgendaMedico(AgendaMedico agMedico) {
        try{
            connection = ConnectionFactory.getConnection();
            final PreparedStatement statement;

            statement = connection.prepareStatement("call createAgendaMedico(?,?,?)");
            statement.setString(1, agMedico.getMedico().getNome().get());
            statement.setString(2, agMedico.getAgenda().getHorario().get());
            statement.setString(3, agMedico.getAgenda().getDia().get());

            if (!statement.execute()) {
                return true;
            }

            ConnectionFactory.closeConnection(connection, statement);

        }catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return false;
    }
}