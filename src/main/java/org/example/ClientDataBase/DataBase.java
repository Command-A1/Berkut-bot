package org.example.ClientDataBase;

import java.sql.ResultSet;

abstract public class DataBase {
    final static private String key = "org.postgresql.Driver";
    public static void driverConnections(){
        try {
            Class.forName(key);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
