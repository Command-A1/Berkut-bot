package org.example.ClientDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Dish extends DataBase {
    private final TreeMap<Integer, ArrayList<String>> mapDataDishes;

    private ArrayList<String> correctDish;
    public Dish(){
        mapDataDishes = new TreeMap<>();
    }

    public void getAllDataAboutDishes(String categoryName) {
        try {
            Statement stmt = databaseConn.createStatement();
            ResultSet dataAboutDish = stmt.executeQuery("select * from " + categoryName);
            while (dataAboutDish.next())
                mapDataDishes.put(Integer.parseInt(dataAboutDish.getString("id")), new ArrayList<>(Arrays.asList(
                        dataAboutDish.getString("name"),
                        dataAboutDish.getString("description"),
                        dataAboutDish.getString("composition"),
                        dataAboutDish.getString("photo"),
                        dataAboutDish.getString("price"))));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public TreeMap<Integer, ArrayList<String>> getMapDataDishes(){
        return mapDataDishes;
    }

    public void recordCorrectDish(int id) {
        correctDish = new ArrayList<>(mapDataDishes.get(id));
    }

    public ArrayList<String> getCorrectDish() {
        return correctDish;

    }
}
