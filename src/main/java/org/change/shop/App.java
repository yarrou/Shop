package org.change.shop;

import java.sql.*;
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

    public static void main(String[] args) throws SQLException {
        App app = new App();

        Statement st = app.connect().createStatement();
        ResultSet rs = st.executeQuery("SELECT id,name,age,city FROM customers ");
        while (rs.next())
        {
            System.out.print("request completed ");
            System.out.println(rs.getInt("id")+" : " +rs.getString("name")+ " : "+rs.getInt("age")+" : "+ rs.getString("city"));
        }
        rs.close();
        st.close();
    }
}
