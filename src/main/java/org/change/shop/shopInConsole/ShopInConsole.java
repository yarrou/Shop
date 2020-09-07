package org.change.shop.shopInConsole;

import org.change.shop.database.DBManager;
import org.change.shop.database.models.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ShopInConsole {
    private DBManager dbManager;
    public void addingBuyer(){
        BufferedReader reader = null;
        try{
            System.out.println("Выбрано: Добавить покупателя. Введите имя покупателя: ");
            reader = new BufferedReader(new InputStreamReader(System.in));
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
        }
        catch (SQLException e){
            System.out.println("Произошла ошибка.");
        }
        catch (IOException e){
            System.out.println("Произошла ошибка.");
        }
        finally {
            try {
                if(reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("Выберете следующее действие: ");
        }
    }
}
