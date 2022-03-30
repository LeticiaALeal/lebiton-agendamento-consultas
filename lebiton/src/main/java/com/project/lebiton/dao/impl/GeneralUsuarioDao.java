package com.project.lebiton.dao.impl;

import com.project.lebiton.dao.GeneralUsuarioDaoInterface;
import com.project.lebiton.dao.connction.ConnectionFactory;
import com.project.lebiton.model.UsuarioInterface;
import com.project.lebiton.model.impl.Medico;
import com.project.lebiton.model.impl.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GeneralUsuarioDao implements GeneralUsuarioDaoInterface {

    private Connection connection;

    public boolean login(final String usuario, final String senha) {
        try {
            connection = ConnectionFactory.getConnection();
            final ResultSet result;
            final PreparedStatement statement;

            statement = connection.prepareStatement("SELECT * FROM usuario where email = ? and senha = ?");
            statement.setString(1, usuario);
            statement.setString(2, senha);

            result = statement.executeQuery();

            if (result.next()) {
                return true;
            }

            ConnectionFactory.closeConnection(connection, statement, result);

        } catch (final SQLException ex) {
            System.out.println("Erro: " + ex.getStackTrace());
        }

        return false;
    }

    @Override
    public boolean createUser(UsuarioInterface usuario) {

        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = null;

            if (usuario instanceof Medico) {
                statement = connection.prepareStatement("call insertUserMedico(?,?,?,?,?,?)");

                Medico medico = (Medico) usuario;
                statement.setString(1, medico.getNome().get());
                statement.setString(2, medico.getEmail());
                statement.setString(3, medico.getTelefone());
                statement.setString(4, medico.getSenha());
                statement.setString(5, medico.getCrm().get());
                statement.setString(6, medico.getEspecialidade().get());

            } else if (usuario instanceof Paciente) {
                statement = connection.prepareStatement("call insertUserPaciente(?,?,?,?,?)");

                Paciente paciente = (Paciente) usuario;
                statement.setString(1, paciente.getNome().get());
                statement.setString(2, paciente.getEmail());
                statement.setString(3, paciente.getTelefone());
                statement.setString(4, paciente.getSenha());
                statement.setString(5, paciente.getCpf());
            }

            if (!statement.execute()) {
                return true;
            }

            ConnectionFactory.closeConnection(connection, statement);

        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        }

        return false;
    }
}