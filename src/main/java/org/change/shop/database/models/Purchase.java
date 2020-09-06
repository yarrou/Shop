package org.change.shop.database.models;

import java.sql.Date;

public class Purchase {
    private int id;
    private int customer_id;
    private int product_id;
    private Date date;

    public Purchase(int id, int customer_id, int product_id, java.sql.Date date) {
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", product_id=" + product_id +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public void setDate(java.util.Date date1) {
        this.date = new java.sql.Date(date1.getTime());
    }
}
