CREATE TABLE Usuario (
    id NUMBER PRIMARY KEY,
    nome_usuario VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    senha VARCHAR2(255) NOT NULL
);

CREATE SEQUENCE usuario_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE FonteRenovavel (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(100) UNIQUE NOT NULL,
    descricao CLOB
);

CREATE SEQUENCE fonte_renovavel_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE ProjetoEnergia (
    id NUMBER PRIMARY KEY,
    nome VARCHAR2(150) NOT NULL,
    localizacao VARCHAR2(200) NOT NULL,
    capacidade DECIMAL(10, 2) NOT NULL,
    id_fonte_renovavel NUMBER NOT NULL,
    FOREIGN KEY (id_fonte_renovavel) REFERENCES FonteRenovavel(id)
);

CREATE SEQUENCE projeto_energia_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE Investimento (
    id NUMBER PRIMARY KEY,
    id_usuario NUMBER NOT NULL,
    id_projeto_energia NUMBER NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_projeto_energia) REFERENCES ProjetoEnergia(id) ON DELETE CASCADE
);

CREATE SEQUENCE investimento_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE DadoEnergia (
    id NUMBER PRIMARY KEY,
    id_projeto_energia NUMBER NOT NULL,
    energia_gerada DECIMAL(10, 2) NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (id_projeto_energia) REFERENCES ProjetoEnergia(id) ON DELETE CASCADE
);

CREATE SEQUENCE dado_energia_seq START WITH 1 INCREMENT BY 1;
