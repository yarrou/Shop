package org.change.shop.database;

import org.change.shop.database.models.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class DBManagerTest {
    DBManager dbManager ;

    @org.junit.jupiter.api.Test
    @Before
    public void setUp(){
        dbManager = new DBManager(System.getenv("PG_CONN_STRING"),System.getenv("PG_USR"),System.getenv("PG_PSSWRD"));
    }
    @Test
    public void getCustomerTest() throws SQLException {
        int testId = 3;
        Customer customer=dbManager.getCustomer(testId);
        Assert.assertEquals(testId,customer.getId());
    }
}