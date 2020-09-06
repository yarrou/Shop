package org.change.shop.database;

import org.change.shop.database.models.Customer;
import org.change.shop.database.models.Product;
import org.change.shop.database.models.Purchase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.sql.*;
import java.util.ArrayList;


public class DBManagerTest {
    DBManager dbManager;
    Customer customer;
    Product product;
    Purchase purchase;
    int indexCustomer;
    int indexProduct;
    int indexPurchase;

    @org.junit.jupiter.api.Test
    @Before
    public void setUp() throws SQLException {
        dbManager = new DBManager(System.getenv("PG_CONN_STRING"), System.getenv("PG_USR"), System.getenv("PG_PSSWRD"));
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = dbManager.getConn().createStatement();

            resultSet = statement.executeQuery("select max(id) from products");
            resultSet.next();
            indexProduct = resultSet.getInt(1) + 1;
            product = new Product(-1, "test", 1, "test");

            resultSet = statement.executeQuery("select max(id) from customers");
            resultSet.next();
            indexCustomer = resultSet.getInt(1) + 1;
            customer = new Customer(-1, "test", "test@gmail.com", "test", 1);


            resultSet = statement.executeQuery("select max(id) from purchases");
            resultSet.next();
            indexPurchase = resultSet.getInt(1) + 1;
            purchase = new Purchase(-1, indexCustomer, indexProduct, new java.sql.Date(new java.util.Date().getTime()));
            ArrayList<Purchase> list = new ArrayList<>();
            list.add(purchase);
            customer.setPurchaseList(list);

            dbManager.saveCustomer(customer);
            dbManager.saveProduct(product);
            dbManager.savePurchase(purchase);
            customer.setId(indexCustomer);
            purchase.setId(indexPurchase);
            product.setId(indexProduct);
        } finally {
            if (statement != null) statement.close();
            if (resultSet != null) resultSet.close();
        }
    }

    @Test
    public void getCustomerTest() throws SQLException {
        Customer testCustomer = dbManager.getCustomer(indexCustomer);
        Assert.assertEquals(customer, testCustomer);


    }


    @Test
    public void getPurchaseTest() throws SQLException {
        Purchase purchaseTest = dbManager.getPurchase(indexPurchase);
        System.out.println(purchaseTest);
        System.out.println(purchase);
        System.out.println(purchaseTest.equals(purchase));
        Assert.assertEquals(purchaseTest, purchase);

    }

    @org.junit.jupiter.api.Test
    public void getProductTest() throws SQLException {
        Product productTest = dbManager.getProduct(indexProduct);
        System.out.println();
        Assert.assertEquals(product, productTest);


    }

    @AfterEach
    public void tearDown() throws SQLException {
        customer.setId(indexCustomer);
        purchase.setId(indexPurchase);
        product.setId(indexProduct);
        dbManager.deleteCustomer(customer);
        dbManager.deletePurchase(purchase);
        dbManager.deleteProduct(product);


    }
}