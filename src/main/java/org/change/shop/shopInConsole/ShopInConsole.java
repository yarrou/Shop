package org.change.shop.shopInConsole;

import org.change.shop.database.DBManager;
import org.change.shop.database.models.Customer;
import org.change.shop.database.models.Product;
import org.change.shop.database.models.Purchase;
import sun.security.pkcs.ParsingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class ShopInConsole {
    private DBManager dbManager;
    private boolean continuation = true;
    private BufferedReader reader;

    public BufferedReader getReader() {
        if (reader == null) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            return reader;
        } else {
            return reader;
        }
    }

    public void closeReader() {
        try {
            if (reader != null) reader.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public ShopInConsole(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void choice() {
        BufferedReader reader = getReader();
        try {
            System.out.println("Добрый день!");
            while (continuation) {
                System.out.println("Выберете действие: ");
                System.out.println("1. Добавить покупателя");
                System.out.println("2. Добавить товар");
                System.out.println("3. Зарегистрировать покупку");
                System.out.println("4. Получить отчёт по городам");
                System.out.println("5. Получить отчёт по покупателям");
                System.out.println("6. Вывести список покупателей");
                System.out.println("7. Вывести список продуктов");
                System.out.println("8. Выйти");
                String choiceUser = reader.readLine();
                if (choiceUser.equals("8")) break;
                else if (choiceUser.equals("1")) addingBuyer();
                else if (choiceUser.equals("2")) addProduct();
                else if (choiceUser.equals("3")) addPurchase();
                else if (choiceUser.equals("4")) cityReport();
                else if (choiceUser.equals("5")) customerReport();
                else if (choiceUser.equals("6")) buyerReview();
                else if (choiceUser.equals("7")) productReviev();
                else System.out.println("Некорректный ввод");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeReader();
        }
    }

    public void addingBuyer() {
        try {
            System.out.println("Выбрано: Добавить покупателя. Введите имя покупателя: ");
            BufferedReader reader = getReader();
            String nameBuyer = reader.readLine();
            System.out.println("Введите email покупателя: ");
            String emailBuyer = reader.readLine();
            System.out.println("Введите город проживания покупателя: ");
            String cityBuyer = reader.readLine();
            System.out.println("Введите возраст покупателя");
            int ageBuyer = Integer.parseInt(reader.readLine());
            Customer buyer = new Customer(-1, nameBuyer, emailBuyer, cityBuyer, ageBuyer);
            dbManager.saveCustomer(buyer);
            System.out.println("Покупатель добавлен.");
        } catch (NumberFormatException e) {
            System.out.println("Некорректно введен возраст покупателя.");
        } catch (SQLException e) {
            System.out.println("Не удалось добавить покупателя.");
        } catch (IOException e) {
            System.out.println("Произошла ошибка.");
        }
    }

    public void addProduct() throws IOException {
        try {
            System.out.println("Выбрано : Добавить продукт. Введите название продукта: ");
            String nameProduct = getReader().readLine();
            System.out.println("Введите стоимость продукта: ");
            int costProduct = Integer.parseInt(reader.readLine());
            System.out.println("Введите заметку к продукту: ");
            String noteProduct = reader.readLine();
            Product product = new Product(-1, nameProduct, costProduct, noteProduct);
            dbManager.saveProduct(product);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить продукт.");
        } catch (NumberFormatException e) {
            System.out.println("Некорректно введена стоимость.");
        }
    }

    public void addPurchase() {
        try {
            System.out.println("Выбрано : Добавить покупку. Введите id покупателя: ");
            int customerId = Integer.parseInt(getReader().readLine());
            System.out.println("Введите id товара: ");
            int productId = Integer.parseInt(getReader().readLine());
            java.util.Date date = new java.util.Date();
            Purchase purchase = new Purchase(-1, customerId, productId, (Date) date);
            dbManager.savePurchase(purchase);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод.");
        } catch (SQLException e) {
            System.out.println("Не удалось зарегистрировать покупку.");
        } catch (IOException e) {
            System.out.println("Произошла ошибка.");
        }
    }

    public void cityReport() {
        System.out.println("Выбрано отчет по городам. Введите номер месяца: ");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int month = Integer.parseInt(getReader().readLine());
            statement =  dbManager.getConn().prepareStatement("select c.city,count(p.*),sum(p2.price) from purchases p join customers c on c.id = p.customer_id join products p2 on p2.id = p.product_id where extract(month from p.date) = ? group by city");
            statement.setInt(1,month);
            resultSet = statement.executeQuery();
            System.out.println("Город  |  Количество  |  Стоимость");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1) + " , "+resultSet.getInt(2)+" , "+resultSet.getInt(3));
            }
        }
        catch (NumberFormatException e){
            System.out.println("Неправильный формат ввода месяца.");
        }
        catch (IOException e){
            System.out.println("Error!");
        }
        catch (SQLException e){
            System.out.println("Не удалось выполнить запрос");
        }
        finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    public void customerReport(){
        System.out.println("Выбран отчет по покупателям.");
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = dbManager.getConn().createStatement();
            resultSet = statement.executeQuery("select c.name,c.email,sum(p2.price) from purchases p join customers c on p.customer_id = c.id join products p2 on p.product_id = p2.id group by c.name,c.email order by sum desc");
            System.out.println("Имя    |    Емейл   | Общий доход от покупателя");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+" , "+ resultSet.getString(2 )+ " , " + resultSet.getInt(3));
            }
        }
        catch (SQLException e){
            System.out.println("Не удалось выполнить запрос.");
            e.printStackTrace();
        }
    }

    public void buyerReview(){
        System.out.println("Выбран обзор покупателей.");
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = dbManager.getConn().createStatement();
            resultSet = statement.executeQuery("select * from customers");
            System.out.println("Id  |   Имя   |   Емейл   |   Город   | Возраст");
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+" , "+resultSet.getString(2)+" , "+resultSet.getString(3)+" , "+resultSet.getString(4)+" , "+resultSet.getInt(5));
            }
        }
        catch (SQLException e){
            System.out.println("Не удалось выполнить запрос.");
        }
    }
    public void productReviev(){
        System.out.println("Выбран обзор продуктов.");
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = dbManager.getConn().createStatement();
            resultSet = statement.executeQuery("select * from products");
            System.out.println("Id  |   Название   |   Стоимость   |   Примечане");
            while (resultSet.next()){
                System.out.println(resultSet.getInt(1)+" , "+resultSet.getString(2)+" , "+resultSet.getString(3)+" , "+resultSet.getString(4));
            }
        }
        catch (SQLException e){
            System.out.println("Не удалось выполнить запрос.");
        }
    }

}
