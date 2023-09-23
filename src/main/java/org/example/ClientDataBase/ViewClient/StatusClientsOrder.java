package org.example.ClientDataBase.ViewClient;

import lombok.SneakyThrows;
import org.example.ClientDataBase.DataBase;
import org.example.Telegram.Models.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatusClientsOrder extends DataBase {


    private static ResultSet getStatusOrderClient() {
        try {
            Statement stmt = databaseConn.createStatement();
            return stmt.executeQuery("select idclient,orderid,waitername from orderclients  where (confirmorder = 'true'and sendmessage = 'false')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setStatusSendMessage() {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("update orderclients set sendmessage  = 'true' where confirmorder = 'true'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private static void outputClientOrderStatus() {
        ResultSet ordersConfirm = getStatusOrderClient();
        while (ordersConfirm.next()) {
            ClientMessageOrder.sendPhotoWaiterWithMessageOrderId(
                    ordersConfirm.getString("idclient"),
                    ordersConfirm.getString("orderid"),
                    ordersConfirm.getString("waitername"),
                    "file:///C:/Users/werog/Downloads/fon");
        }
        setStatusSendMessage();
    }

    public static void runThreadOrderClient() {
        Thread run = new Thread(() -> {
            while (true) {
                try {
                    StatusClientsOrder.outputClientOrderStatus();
                    Thread.sleep(1000); //1000 - 1 сек
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        run.start(); // заводим
    }
}
