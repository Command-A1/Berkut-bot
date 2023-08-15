package org.example.ClientDataBase;
import java.sql.*;

public class Order extends DataBase {
    public void createOrder(String userId) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order (id, table) values ('" + userId + "')");
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

    public void changeProcessing(String userId) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order (proccesing) values ('true')  where userid like '" + userId + "'");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeAccepting(String userId) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order (accepting) values ('true')  where userid like '" + userId + "'");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeState (String userId) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into order (accepting) values ('true')  where userid like '" + userId + "'");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

