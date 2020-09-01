package org.change.shop.database.models;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String city;
    private int age;

    public Customer(int id, String name, String email, String city, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.age = age;
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
}
