package org.change.shop.database;

import org.change.shop.database.models.Customer;
import org.change.shop.database.models.Product;
import org.change.shop.database.models.Purchase;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private Connection conn;
    private  String url;
    private String user;
    private  String password;
    private int id;

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
        ArrayList<Purchase> list = new ArrayList<>();

        PreparedStatement ps = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            conn.prepareStatement("select * from purchases where customer_id = ?");
            ps.setInt(1, id);
            ResultSet resultSetOfPurchases = ps.executeQuery();
            while (resultSetOfPurchases.next()) {
                Purchase purchase = new Purchase(resultSetOfPurchases.getInt(1), resultSetOfPurchases.getInt(2),
                        resultSetOfPurchases.getInt(3), resultSetOfPurchases.getString(4));
                list.add(purchase);
            }
            preparedStatement = conn.prepareStatement("select * from customers where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            Customer customer = null;
            resultSet.next();
            customer = new Customer(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), list);
            return customer;
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
            if(ps != null) ps.close();
            if(resultSet != null) resultSet.close();
        }
    }



    public void deleteCustomer(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            conn.prepareStatement("delete from customers where id = ?");
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();
        }
        finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }


    public void saveCustomer(Customer customer) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            if (((Integer) customer.getId() == null) || (customer.getId() == -1)) {
                preparedStatement = conn.prepareStatement(
                        "insert into customers (name,email,city,age) values (?,?,?,?)");
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.setString(3, customer.getCity());
                preparedStatement.setInt(4, customer.getAge());
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = conn.prepareStatement("update customers set name=?,email = ?,city = ?, age = ? where id = ?");
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getEmail());
                preparedStatement.setString(3, customer.getCity());
                preparedStatement.setInt(4, customer.getAge());
                preparedStatement.setInt(5, customer.getId());
                preparedStatement.executeUpdate();
            }
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
        }
    }

    public Purchase getPurchase(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            conn.prepareStatement("select * from purchases where id = ?");
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Purchase purchase = new Purchase(id,resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3));
        return  purchase;
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
            if(resultSet != null) resultSet.close();
        }
    }


    public void deletePurchase( Purchase purchase) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            conn.prepareStatement("delete from purchases where id = ?");
            preparedStatement.setInt(1, purchase.getId());
            preparedStatement.executeUpdate();
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
        }
    }

    public void savePurchase(Purchase purchase) throws SQLException{
        PreparedStatement preparedStatement = null;
        try {
            if (((Integer) purchase.getId() == null) || (purchase.getId() == -1)) {
                preparedStatement = conn.prepareStatement(
                        "insert into purchases (customer_id,product_id,date) values (?,?,?)");
                preparedStatement.setInt(1, purchase.getCustomer_id());
                preparedStatement.setInt(2, purchase.getProduct_id());
                preparedStatement.setString(3, purchase.getDate());
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = conn.prepareStatement("update purchases set customer_id =?,product_id = ?,date = ? where id = ?");
                preparedStatement.setInt(1, purchase.getCustomer_id());
                preparedStatement.setInt(2, purchase.getProduct_id());
                preparedStatement.setString(3, purchase.getDate());
                preparedStatement.setInt(4, purchase.getId());
                preparedStatement.executeUpdate();
            }
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
        }
    }

    public Product getProduct(int id) throws SQLException{
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement("select * from products where id = 7");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Product product = new Product(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4));
            preparedStatement.close();
            resultSet.close();
            return product;
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
            if(resultSet!= null) resultSet.close();
        }
    }

    public void deleteProduct(Product product) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("delete from products where id = ?");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
        }
        preparedStatement.close();
    }

    public void saveProduct(Product product) throws SQLException{
        PreparedStatement preparedStatement = null;
        try {
            if (((Integer) product.getId() == null) || (product.getId() == -1)) {
                preparedStatement = conn.prepareStatement(
                        "insert into products (name,price,note) values (?,?,?)");
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2, product.getPrice());
                preparedStatement.setString(3, product.getNote());
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = conn.prepareStatement("update products set name = ?,price = ?,note = ? where id = ?");
                preparedStatement.setString(1, product.getName());
                preparedStatement.setInt(2, product.getPrice());
                preparedStatement.setString(3, product.getNote());
                preparedStatement.setInt(4, product.getId());
                preparedStatement.executeUpdate();
            }
        }
        finally {
            if(preparedStatement != null) preparedStatement.close();
        }
    }

}
