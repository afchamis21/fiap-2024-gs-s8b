package com.fiap.chamis.application.repo.core;

import java.sql.Connection;
import java.sql.Statement;

public class DbManager extends BaseRepository {
    public void inicializarBanco() {
        String[] createTablesSQL = {
            """
            CREATE TABLE Usuario (
                id NUMBER PRIMARY KEY,
                nome_usuario VARCHAR2(100) NOT NULL,
                email VARCHAR2(100) UNIQUE NOT NULL,
                senha VARCHAR2(255) NOT NULL
            )
            """,
            "CREATE SEQUENCE usuario_seq START WITH 1 INCREMENT BY 1",
            """
            CREATE TABLE FonteRenovavel (
                id NUMBER PRIMARY KEY,
                nome VARCHAR2(100) UNIQUE NOT NULL,
                descricao CLOB
            )
            """,
            "CREATE SEQUENCE fonte_renovavel_seq START WITH 1 INCREMENT BY 1",
            """
            CREATE TABLE ProjetoEnergia (
                id NUMBER PRIMARY KEY,
                nome VARCHAR2(150) NOT NULL,
                localizacao VARCHAR2(200) NOT NULL,
                capacidade DECIMAL(10, 2) NOT NULL,
                id_fonte_renovavel NUMBER NOT NULL,
                FOREIGN KEY (id_fonte_renovavel) REFERENCES FonteRenovavel(id)
            )
            """,
            "CREATE SEQUENCE projeto_energia_seq START WITH 1 INCREMENT BY 1",
            """
            CREATE TABLE Investimento (
                id NUMBER PRIMARY KEY,
                id_usuario NUMBER NOT NULL,
                id_projeto_energia NUMBER NOT NULL,
                valor DECIMAL(15, 2) NOT NULL,
                FOREIGN KEY (id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE,
                FOREIGN KEY (id_projeto_energia) REFERENCES ProjetoEnergia(id) ON DELETE CASCADE
            )
            """,
            "CREATE SEQUENCE investimento_seq START WITH 1 INCREMENT BY 1",
            """
            CREATE TABLE DadoEnergia (
                id NUMBER PRIMARY KEY,
                id_projeto_energia NUMBER NOT NULL,
                energia_gerada DECIMAL(10, 2) NOT NULL,
                data DATE NOT NULL,
                FOREIGN KEY (id_projeto_energia) REFERENCES ProjetoEnergia(id) ON DELETE CASCADE
            )
            """,
            "CREATE SEQUENCE dado_energia_seq START WITH 1 INCREMENT BY 1"
        };
    
        try (Connection connection = super.getConnection();
             Statement statement = connection.createStatement()) {
    
            for (String sql : createTablesSQL) {
                statement.executeUpdate(sql);
            }
    
            System.out.println("Tabelas criadas com sucesso!");
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void resetarBanco() {
        String[] resetTablesSQL = {
            "DROP TABLE DadoEnergia CASCADE CONSTRAINTS",
            "DROP TABLE Investimento CASCADE CONSTRAINTS",
            "DROP TABLE ProjetoEnergia CASCADE CONSTRAINTS",
            "DROP TABLE FonteRenovavel CASCADE CONSTRAINTS",
            "DROP TABLE Usuario CASCADE CONSTRAINTS"
        };

        String[] resetSequencesSQL = {
            "DROP SEQUENCE dado_energia_seq",
            "DROP SEQUENCE investimento_seq",
            "DROP SEQUENCE projeto_energia_seq",
            "DROP SEQUENCE fonte_renovavel_seq",
            "DROP SEQUENCE usuario_seq"
        };
    
        try (Connection connection = super.getConnection();
             Statement statement = connection.createStatement()) {
    
            for (String sql : resetTablesSQL) {
                try {
                    statement.executeUpdate(sql);
                } catch (Exception e) {
                    System.err.println("Erro ao excluir tabela: " + e.getMessage());
                }
            }
    
            System.out.println("Tabelas apagadas com sucesso!");
    
            for (String sql : resetSequencesSQL) {
                try {
                    statement.executeUpdate(sql);
                } catch (Exception e) {
                    System.err.println("Erro ao excluir sequências: " + e.getMessage());
                }
            }

            System.out.println("Sequências apagadas com sucesso!");

            this.inicializarBanco();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
