package org.example.ClientDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Categories extends DataBase {

    final private static Connection databaseConn;

    static {
        try {
            databaseConn = DriverManager.getConnection("jdbc:postgresql://containers-us-west-117.railway.app:7441/railway",
                    "postgres", "29US5H0SPDjZ67I3C6Sp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getAllCategories() {
        try {
            List<String> allCategories = new ArrayList<>();
            Statement stmt = databaseConn.createStatement();
            ResultSet categories = stmt.executeQuery("select name from categories");
            while (categories.next())
                allCategories.add(categories.getString("name"));
            return allCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}