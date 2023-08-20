package org.example.ClientDataBase;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class OrderDB extends DataBase {
    public void createOrderInDB(String userid, String table, String dishesId, Date timeCreateMessage) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into orderclients  (idclient, tableclient, disheid, dateconfirmorder) values('" + userid + "','" + table + "','"  + dishesId + "','"+ timeCreateMessage. +"')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
