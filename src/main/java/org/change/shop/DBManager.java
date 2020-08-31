package org.change.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private Connection conn;
    private  String url;
    private String user;
    private  String password;

    public DBManager(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to the server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConn() {
        return conn;
    }
}
