package com.gestao.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Conexao.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Desculpe, não foi possível encontrar o arquivo application.properties");
            } else {
                properties.load(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConexao() throws SQLException {
        try {
            // Garante que o driver JDBC do MySQL está carregado
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC do MySQL não encontrado.", e);
        }
    }
}