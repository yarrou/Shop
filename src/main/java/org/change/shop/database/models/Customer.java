package org.change.shop.database.models;

import java.util.List;
import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String city;
    private int age;
    private List<Purchase> purchaseList;

    public Customer(int id, String name, String email, String city, int age, List<Purchase> list) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.age = age;
        this.purchaseList = list;
    }

    public Customer(int id, String name, String email, String city, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", purchaseList=" + purchaseList +
                '}';
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Customer() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.getId() && name.equals(customer.getName()) && email.equals(customer.getEmail()) &&
                city.equals(customer.getCity()) && age == customer.getAge();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, city, age, purchaseList);
    }
}
