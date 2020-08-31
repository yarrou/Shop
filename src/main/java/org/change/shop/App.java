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
        Connection con=app.connect();
        PreparedStatement pst = con.prepareStatement("select ?,?,?,? from customers");
        pst.setString(1,"id");
        pst.setString(2,"name");
        pst.setString(3,"age");
        pst.setString(4,"city");
        ResultSet rs=pst.executeQuery();
        System.out.println("request completed");
        while (rs.next()){
            System.out.println(rs.getInt("id")+" : " +rs.getString("name")+ " : "+rs.getInt("age")+" : "+ rs.getString("city"));
        }
        rs.close();
        pst.close();
    }
}
