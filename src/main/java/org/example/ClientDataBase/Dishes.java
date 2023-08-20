package org.example.ClientDataBase;

import java.sql.*;
import java.util.*;

public class Dishes extends DataBase implements ISortedMapBD {
    private TreeMap<Integer, String> treeMap;

    public ArrayList<Map<Integer, String>> getAllCategoriesNameDishes(String categoryName) {

        try {
            treeMap = new TreeMap<>();
            Statement stmt = databaseConn.createStatement();
            ResultSet categories = stmt.executeQuery("select id, name from " + categoryName);
            while (categories.next())
                treeMap.put(Integer.parseInt(categories.getString("id")), categories.getString("name"));
            return sortedCategoriesTreeMap(treeMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TreeMap<Integer, String> getTreeMap() {
        return treeMap;
    }
}