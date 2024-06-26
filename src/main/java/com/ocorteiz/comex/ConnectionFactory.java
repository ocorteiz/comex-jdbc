package com.ocorteiz.comex;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection(){
        try {
            System.out.println("Conexão estabelecida");
            return createDataSource().getConnection();
        } catch (SQLException e) {
            System.out.println("Não foi possivel estabelecer conexão");
            throw new RuntimeException(e);
        }
    }

    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/comex");
        config.setUsername();
        config.setPassword();
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

}
