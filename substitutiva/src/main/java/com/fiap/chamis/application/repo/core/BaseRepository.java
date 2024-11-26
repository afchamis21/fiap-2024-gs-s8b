package com.fiap.chamis.application.repo.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    public Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
        String user = "RM97891";
        String password = "210501";

        Connection connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(true);

        return connection;
    }
}