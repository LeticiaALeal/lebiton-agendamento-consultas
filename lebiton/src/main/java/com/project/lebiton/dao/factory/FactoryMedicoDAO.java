package com.project.lebiton.dao.factory;

import com.project.lebiton.dao.MedicoDaoInterface;
import com.project.lebiton.dao.impl.MedicoDao;

public class FactoryMedicoDAO {
	
	public static MedicoDaoInterface criarMedicodao() {
		return new MedicoDao();
	}

}
