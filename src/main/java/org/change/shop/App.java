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
        /*Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("SELECT id,name,age,city FROM customers ");
        while (rs1.next())
        {
            System.out.print("request completed ");
            System.out.println(rs1.getInt("id")+" : " +rs1.getString("name")+ " : "+rs1.getInt("age")+" : "+ rs1.getString("city"));
        }*/
        PreparedStatement pst = con.prepareStatement("insert into customers (name,email,city,age) values(?,?,?,?)");
        pst.setString(1,"Victor");
        pst.setString(2,"victorL@tut.by");
        pst.setString(3,"Drozdi");
        pst.setInt(4,44);
        pst.executeUpdate();
        System.out.println("update completed");
        /*while (rs.next()){
            System.out.println(rs.getInt("id")+" : " +rs.getString("name")+ " : "+rs.getInt("age")+" : "+ rs.getString("city"));
        }
        rs.close();
        */
        pst.close();
    }
}
