package org.change.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class App {
    private final String url = System.getenv("PG_CONN_STRING");
    private final String user = System.getenv("PG_USR");
    private final String password = System.getenv("PG_PSSWRD");

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
