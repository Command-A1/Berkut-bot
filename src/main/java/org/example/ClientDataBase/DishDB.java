package org.example.ClientDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DishDB extends DataBase {
    private final TreeMap<Integer, Dish> mapDataDishes;

    private Dish correctDish;
    public DishDB(){
        mapDataDishes = new TreeMap<>();
    }

    public void getAllDataAboutDishes(String categoryName) {
        try {
            Statement stmt = databaseConn.createStatement();
            ResultSet dataAboutDish = stmt.executeQuery("select * from " + categoryName);
            while (dataAboutDish.next())
                mapDataDishes.put(Integer.parseInt(dataAboutDish.getString("id")), new Dish(
                        dataAboutDish.getString("name"),
                        dataAboutDish.getString("description"),
                        dataAboutDish.getString("composition"),
                        dataAboutDish.getString("price"),
                        dataAboutDish.getString("photo")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public TreeMap<Integer, Dish> getMapDataDishes(){
        return mapDataDishes;
    }

    public void recordCorrectDish(int id) {
        correctDish = mapDataDishes.get(id);
    }

    public Dish getCorrectDish() {
        return correctDish;

    }
}
