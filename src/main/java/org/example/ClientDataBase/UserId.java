package org.example.ClientDataBase;

import java.sql.*;

public class UserId extends DataBase {

    public ResultSet getAllId() {
        try {
            Statement stmt = databaseConn.createStatement(); // здесь выкидывает ощибку если неправильно вводить сообщения вместо контактов
            return stmt.executeQuery("select userid from usersnumbers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean userIdComparison(long id, ResultSet allUserId) {
        try {
            while (allUserId.next())
                if (Long.toString(id).equals(allUserId.getString("userid"))) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
