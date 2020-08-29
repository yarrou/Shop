package org.change.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class App {
    private final String url = "jdbc:postgresql://database-1.chstl11a7qwq.eu-west-1.rds.amazonaws.com:5432/shop";
    private final String user = "postgres";
    private final String password = "nomorebugs";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        App app = new App();
        app.connect();
    }
}