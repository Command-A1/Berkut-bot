package org.example.ClientDataBase;


import org.example.Telegram.Models.Client;

import java.sql.SQLException;


public class OrderDB extends DataBase {
    public boolean createOrderInDB(String userid, String table, String dishesId, String timeCreateMessage, String orderId) {
        try {
            databaseConn.createStatement().executeUpdate("insert into orderclients  (idclient, tableclient, disheid, dateconfirmorder,orderid,confirmorder) " +
                    "values('" + userid + "','" + table + "','" + dishesId + "','" + timeCreateMessage + "','"+orderId+ "','"+false+"')");
            Client.setOrdersClient(orderId);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
