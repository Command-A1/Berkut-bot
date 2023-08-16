package org.example.ClientDataBase;

import java.sql.*;
import java.util.*;

public class Dishes extends DataBase implements ISortedMapBD {

    public ArrayList<Map<Integer, String>> getAllCategoriesNameDishes(String categoryName) {
        try {
            TreeMap<Integer, String> treeMap = new TreeMap<>();
            Statement stmt = databaseConn.createStatement();
            ResultSet categories = stmt.executeQuery("select id, name from " + categoryName);
            while (categories.next())
                treeMap.put(Integer.parseInt(categories.getString("id")), categories.getString("name"));
            return sortedCategoriesTreeMap(treeMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}