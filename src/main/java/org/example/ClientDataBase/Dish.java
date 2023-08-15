package org.example.ClientDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dish extends DataBase {
    public ArrayList<String> getDish(String categoryName) {
        try {
            ArrayList<String> mapCategories = new ArrayList<>();
            Statement stmt = databaseConn.createStatement();
            ResultSet dataAboutDish = stmt.executeQuery("select name, description, composition, photo, prise from " + categoryName);
            dataAboutDish.next();
            mapCategories.add(dataAboutDish.getString("name"));
            mapCategories.add(dataAboutDish.getString("description"));
            mapCategories.add(dataAboutDish.getString("composition"));
            mapCategories.add(dataAboutDish.getString("photo"));
            mapCategories.add(dataAboutDish.getString("prise"));
            return mapCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
