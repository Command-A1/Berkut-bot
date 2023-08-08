package org.example.ClientDataBase;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.*;
import java.util.Scanner;

public class UserNumber extends DataBase implements NumberUtil {
    private String clientNumber;

    final private static Connection databaseConn;

    static {
        try {
            databaseConn = DriverManager.getConnection("jdbc:postgresql://containers-us-west-117.railway.app:7441/railway",
                    "postgres", "29US5H0SPDjZ67I3C6Sp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void sortNumber(Message message) {
        String num = message.getContact().getPhoneNumber().substring(2);
        DataBase.driverConnections();
        if (numberComparison(message.getContact().getPhoneNumber().substring(2), get()))
            set(message.getChatId(), message.getContact().getFirstName(), Long.parseLong(num));
    }

    @Override
    public void set(long id, String userName, long number) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into usersnumbers (userid, username, number) values ('" + id + "','" + userName + "','" + number + "')");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkNumber(String clientNumber) {
        final String numRegex = "^\\d{10}";
        return clientNumber.matches(numRegex);
    }

    @Override
    public ResultSet get() {
        try {
            Statement stmt = databaseConn.createStatement();
            return stmt.executeQuery("select number from usersnumbers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean numberComparison(String number, ResultSet allNumbers) {
        try {
            while (allNumbers.next())
                if (number.equals(allNumbers.getString("number"))) return false;
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
