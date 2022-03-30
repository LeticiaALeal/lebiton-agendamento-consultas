package com.project.lebiton.dao.factory;

import com.project.lebiton.dao.CadastrarDiasAgendaDaoInterface;
import com.project.lebiton.dao.impl.CadastroDiasAgendaDao;

public class FactoryCadastroDiasAgendaDAO {
	
	public static CadastrarDiasAgendaDaoInterface criarCadastroDiasAgendadao() {
		return new CadastroDiasAgendaDao();
	}

}
