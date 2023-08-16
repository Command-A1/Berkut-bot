package org.example.ClientDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbLibrary extends DataBase{

    static public ArrayList<String> allNameCategories;
    static public ArrayList<ArrayList<String>> allDishesName;

    public void setAllNameCategories(ArrayList<String> allNameCategories) {
        try {
            ArrayList<String> allCategories = new ArrayList<>();
            Statement stmt = databaseConn.createStatement();
            ResultSet categories = stmt.executeQuery("select name from categories");
            while (categories.next())
                allCategories.add(categories.getString("name"));
            DbLibrary.allNameCategories = allNameCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAllDishesName(HashMap<Integer,String> tablesName){
        for (Map.Entry entry: tablesName.entrySet()) {
            try {
                ArrayList<String> allNamesDishesFroTable = new ArrayList<>();
                Statement stmt = databaseConn.createStatement();
                ResultSet categories = stmt.executeQuery("select name from " + entry.getValue());
                while (categories.next())
                    allNamesDishesFroTable.add(categories.getString("name"));
                DbLibrary.allDishesName.add(allNamesDishesFroTable);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}