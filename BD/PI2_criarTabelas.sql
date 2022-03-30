-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema teste
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema teste
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS lebiDb;
USE lebiDb ;

-- -----------------------------------------------------
-- Table `teste`.`agenda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS agenda (
  id INT NOT NULL AUTO_INCREMENT,
  horario VARCHAR(20) NULL DEFAULT NULL,
  dia VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table `teste`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  telefone VARCHAR(14) NOT NULL,
  senha VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table `teste`.`medicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS medicos (
  idUsuario INT NOT NULL,
  especialidade VARCHAR(100) NULL DEFAULT NULL,
  CRM VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (idUsuario),
  CONSTRAINT medicos_ibfk_1
    FOREIGN KEY (idUsuario)
    REFERENCES usuario (id));


-- -----------------------------------------------------
-- Table `teste`.`agenda_medico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS agenda_medico (
  id INT NOT NULL AUTO_INCREMENT,
  idMedico INT NULL DEFAULT NULL,
  idAgenda INT NULL DEFAULT NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (idMedico) REFERENCES medicos (idUsuario),
    FOREIGN KEY (idAgenda)REFERENCES agenda (id));


-- -----------------------------------------------------
-- Table `teste`.`consulta_paciente`
-- -----------------------------------------------------
drop table consulta_paciente;
CREATE TABLE IF NOT EXISTS consulta (
id INT NOT NULL AUTO_INCREMENT,
idPaciente INT NOT NULL,
emailPaciente varchar(100),
emailMedico varchar(100),
idMedico INT NOT NULL,
horario VARCHAR(20) NULL DEFAULT NULL,
dia VARCHAR(20) NULL DEFAULT NULL,
PRIMARY KEY (id),
FOREIGN KEY (idMedico) REFERENCES medicos (idUsuario),
FOREIGN KEY (idPaciente)REFERENCES pacientes (idUsuario));


-- -----------------------------------------------------
-- Table `teste`.`pacientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pacientes (
  idUsuario INT NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  endereco VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  CONSTRAINT pacientes_ibfk_1
    FOREIGN KEY (idUsuario)
    REFERENCES usuario (id));

-- -----------------------------------------------------
-- Placeholder table for view `teste`.`view_agenda_medico`
-- -----------------------------------------------------
create view view_agenda_medico as
select u.nome 'nome', m.especialidade 'especialidade', a.dia 'dia', a.horario 'horario'
from agenda_medico am
inner join medicos m on m.idUsuario = am.idMedico
inner join agenda a on a.id = am.idAgenda
inner join usuario u on u.id = m.idUsuario;
