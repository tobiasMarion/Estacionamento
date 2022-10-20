CREATE DATABASE IF NOT EXISTS estacionamento;
USE estacionamento;

CREATE TABLE vaga (
    idVaga int NOT NULL AUTO_INCREMENT,
    numero int NOT NULL,
    rua varchar(100) NOT NULL,
    obliqua boolean NOT NULL,
    PRIMARY KEY (idVaga)
);
