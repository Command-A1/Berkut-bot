package org.example.ClientDataBase;


import java.sql.SQLException;


public class OrderDB extends DataBase {
    public boolean createOrderInDB(String userid, String table, String dishesId, String timeCreateMessage, String orderId) {
        try {
            databaseConn.createStatement().executeUpdate("insert into orderclients  (idclient, tableclient, disheid, dateconfirmorder,orderid) " +
                    "values('" + userid + "','" + table + "','" + dishesId + "','" + timeCreateMessage + "','"+orderId+"')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
