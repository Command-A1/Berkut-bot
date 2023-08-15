package org.example.ClientDataBase;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Categories extends DataBase implements ISortedMapBD{


    public ArrayList<Map<Integer, String>> getAllCategories() {
        try {
            TreeMap<Integer, String> treeMap = new TreeMap<>();
            Statement stmt = databaseConn.createStatement();
            ResultSet categories = stmt.executeQuery("select * from categories");

            while (categories.next())
                treeMap.put(Integer.parseInt(categories.getString("id")), categories.getString("name"));
            return sortedCategoriesTreeMap(treeMap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    private ArrayList<Map<Integer, String>> sortedCategoriesTreeMap(){
//        hashMapArrayListCategories.add(0, new TreeMap<>());
//        int c = 0;
//        int i = 0;
//
//        for (Map.Entry entry: treeMap.entrySet()){
//            hashMapArrayListCategories.get(c).put((Integer) entry.getKey(), (String) entry.getValue());
//            i++;
//            if (i == 4 && treeMap.size()-1 != (Integer)entry.getKey()) {
//                i = 0;
//                c++;
//                hashMapArrayListCategories.add(c, new TreeMap<>());
//            }
//        }
//        return hashMapArrayListCategories;
//    }
}