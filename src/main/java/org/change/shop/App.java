package org.change.shop;

import org.change.shop.database.DBManager;
import org.change.shop.shopInConsole.ShopInConsole;


import java.sql.*;

public class App {
    private static final String url = System.getenv("PG_CONN_STRING");
    private static final String user = System.getenv("PG_USR");
    private static final String password = System.getenv("PG_PSSWRD");



    public static void main(String[] args) throws SQLException {
        //DBManager manager = new DBManager(url,user,password);
        ShopInConsole shop = new ShopInConsole(new DBManager(url,user,password));
        shop.choice();
        //shop.productReviev();


    }
}
