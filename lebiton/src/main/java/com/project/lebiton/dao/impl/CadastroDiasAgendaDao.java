package com.project.lebiton.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.project.lebiton.dao.CadastrarDiasAgendaDaoInterface;
import com.project.lebiton.dao.connction.ConnectionFactory;
import com.project.lebiton.model.impl.Agenda;

public class CadastroDiasAgendaDao implements CadastrarDiasAgendaDaoInterface {
	
	private Connection connection;
	
	public boolean cadastrarDiasAgenda (final Agenda agenda) {
		try {
			connection = ConnectionFactory.getConnection();
			final PreparedStatement statement;
			
			statement = connection.prepareStatement("insert into agenda (horario, dia) values (?, ?)");
			statement.setString(1, agenda.getHorario().get());
			statement.setString(2, agenda.getDia().get());
			
            if (!statement.execute()) {
                return true;
            }

            ConnectionFactory.closeConnection(connection, statement);

		} catch (final Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
		return false;
	}
}