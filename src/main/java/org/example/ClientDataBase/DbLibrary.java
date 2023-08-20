package org.example.ClientDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DbLibrary extends DataBase implements ISortedMapBD{

    static public ArrayList<Map<Integer, String>> allCategoriesName;
    static public TreeMap<Integer, Map<Integer, String>> allDishesName;
    static public TreeMap<Integer, ArrayList<String>> mapDataDishes;
    //Map<Integer, String>
    public void getAllCategoriesNameDishes(String categoryName) {
        try {
            TreeMap<Integer, String> treeMap = new TreeMap<>();
            Statement stmt = databaseConn.createStatement();
            ResultSet categories = stmt.executeQuery("select * from categories");

            while (categories.next())
                treeMap.put(Integer.parseInt(categories.getString("id")), categories.getString("name"));
            DbLibrary.allCategoriesName = sortedCategoriesTreeMap(treeMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getAllNameDishes(HashMap<Integer,String> categoryName) {
        try {
            int counter = 0;
            for (Map.Entry entry: categoryName.entrySet()) {

                TreeMap<Integer, String> allCategories = new TreeMap<>();
                Statement stmt = databaseConn.createStatement();
                ResultSet categories = stmt.executeQuery("select id, name from " + entry.getValue());
                while(categories.next())
                    allCategories.put(Integer.parseInt(categories.getString("id")), categories.getString("name"));
                DbLibrary.allDishesName.put(counter,allCategories);
                counter++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAllDishes(HashMap<Integer,String> categoryName){
        try {
            for (Map.Entry entry: categoryName.entrySet()) {

                Statement stmt = databaseConn.createStatement();
                ResultSet dataAboutDish = stmt.executeQuery("select * from " + entry.getValue());
                while (dataAboutDish.next())
                    DbLibrary.mapDataDishes.put(Integer.parseInt(dataAboutDish.getString("id")), new ArrayList<>(Arrays.asList(
                            dataAboutDish.getString("name"),
                            dataAboutDish.getString("description"),
                            dataAboutDish.getString("composition"),
                            dataAboutDish.getString("photo"),
                            dataAboutDish.getString("price"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}