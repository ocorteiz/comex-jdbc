package com.ocorteiz.comex;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        ConnectionFactory connection = new ConnectionFactory();
        connection.getConnection();
    }
}
