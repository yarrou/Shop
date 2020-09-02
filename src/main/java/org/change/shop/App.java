package org.change.shop;

import org.change.shop.database.DBManager;
import org.change.shop.database.models.Customer;

import java.sql.*;

public class App {
    private final String url = System.getenv("PG_CONN_STRING");
    private final String user = System.getenv("PG_USR");
    private final String password = System.getenv("PG_PSSWRD");



    public static void main(String[] args) throws SQLException {
        App app = new App();
        DBManager manager = new DBManager(app.url,app.user,app.password);
        //Connection con=manager.getConn();
        Customer customer = manager.getCustomer(6);
        System.out.println(customer.getId());

    }
}
