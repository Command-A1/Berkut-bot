package org.example.ClientDataBase;


import java.sql.SQLException;


public class OrderDB extends DataBase {
    public boolean createOrderInDB(String userid, String table, String dishesId, String timeCreateMessage) {
        try {
            databaseConn.createStatement().executeUpdate("insert into orderclients  (idclient, tableclient, disheid, dateconfirmorder) values('" + userid + "','" + table + "','"  + dishesId + "','"+ timeCreateMessage +"')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
