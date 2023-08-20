package org.example.ClientDataBase;

import java.sql.*;
import java.util.ArrayList;

public class OrderDB extends DataBase {
    public void createOrderInDB(ArrayList<String> infoAboutOrder) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order values (userid, table, dishesid, time) ('" + infoAboutOrder.get(0) + "'," +
                    "'" + infoAboutOrder.get(1) + "'," +
                    "'" + infoAboutOrder.get(2) + "','"+ infoAboutOrder.get(3) +"')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
