package org.change.shop.database.models;

import java.util.Date;

public class Purchase {
    private int id;
    private int customer_id;
    private int product_id;
    private String date;

    public Purchase(int id, int customer_id, int product_id, String date) {
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
