package org.example.ClientDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OrderDB extends DataBase {
    public void createOrder(String userId) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order (id) values ('" + userId + "')");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addDish(String userId, String dishId) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order (dishesid) values ('" + dishId + "," + "')  where userid like '" + userId + "'");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDish(String dishId){

    }

    public ArrayList<String> getAllDishesIdFromOrder(String userId){
        try {
            Statement stmt = databaseConn.createStatement();
            ResultSet allDishesId =  stmt.executeQuery("select dishesid from order where userid like '" + userId + "'");
            allDishesId.next();
            ArrayList<String> arrayAllDishesId = new ArrayList<>();
            String[] ides = allDishesId.getString("dishesid").split(",");
            arrayAllDishesId.addAll(Arrays.asList(ides));
            return arrayAllDishesId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeState (String userId, String field) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order ("+ field +") values ('true')  where userid like '" + userId + "'");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTable(String userId, String table){
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order (table) values (" + table + ")  where userid like '" + userId + "'");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

