package org.example.ClientDataBase;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.*;

public class UserNumber extends DataBase {
    public void sortNumber(Message message) {
        DataBase.driverConnections();
        if (numberComparison(message.getContact().getPhoneNumber().substring(1), getAllNumbers()))
            setNumber(message.getChatId().toString(), message.getContact().getFirstName(), message.getContact().getPhoneNumber().substring(1));
    }
    public void setNumber(String id, String userName, String number) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into usersnumbers (userid, username, number) values ('" + id + "','" + userName + "','" + number + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public boolean checkNumber(String clientNumber) {
//        final String numRegex = "^\\d{10}";
//        return clientNumber.matches(numRegex);
//    }
    public String getUserName(String userId) {
        try {
            Statement stmt = databaseConn.createStatement();
            ResultSet userName = stmt.executeQuery("select username from usersnumbers where userid like '" + userId + "'");
            userName.next();
            return userName.getString("username");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getAllNumbers() {
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
