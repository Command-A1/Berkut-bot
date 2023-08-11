package org.example.ClientDataBase;

import java.sql.*;

public class UserId {

    final private static Connection databaseConn;

    static {
        try {
            databaseConn = DriverManager.getConnection("jdbc:postgresql://containers-us-west-117.railway.app:7441/railway",
                    "postgres", "29US5H0SPDjZ67I3C6Sp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getAllId() {
        try {
            Statement stmt = databaseConn.createStatement();
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
