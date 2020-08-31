package org.change.shop;

import java.sql.*;
import java.util.concurrent.Callable;

public class App {
    private final String url = System.getenv("PG_CONN_STRING");
    private final String user = System.getenv("PG_USR");
    private final String password = System.getenv("PG_PSSWRD");



    public static void main(String[] args)  {
        App app = new App();
        DBManager manager = new DBManager(app.url,app.user,app.password);
        Connection con=manager.getConn();

    }
}
