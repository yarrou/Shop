package org.change.shop.database;

import org.change.shop.database.models.Customer;

import java.sql.*;

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
    public Customer getCustomer(int id) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("select * from customers where id = ?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.getResultSet();
        Customer customer = new Customer(resultSet.getInt(1),resultSet.getString(2),
                resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5));
        return customer;
    }
}
