package com.project.lebiton.model;

import java.sql.SQLException;

public interface UsuarioInterface {
    boolean logar();
    boolean createUser(UsuarioInterface usuario) throws SQLException, Exception;
}
