//package org.example.ClientDataBase;
//
//import java.sql.*;
//
//public class Order extends DataBase implements OrderUtil {
//    final private static Connection databaseConn;
//
//    static {
//        try {
//            databaseConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/menu_db",
//                    "postgres","120304");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Override
//    public void createNewOrder() {
//        Table table = new Table();
//        addIdAndTableNumber(Integer.toString(createId()), Integer.toString(table.askTableNumber()));
//    }
//
//    @Override
//    public int createId() {
//        return (int) (Math.random() * (9999));
//    }
//
//    @Override
//    public void adDish(String nameDish) {
//
//    }
//
//    @Override
//    public void deleteDish(String nameDish) {
//
//    }
//
//    @Override
//    public String showAllDish() {
//        return null;
//    }
//
//    @Override
//    public void orderProcessing() {
//
//    }
//
//    @Override
//    public void orderAcceptance() {
//
//    }
//
//    @Override
//    public String AddWaiter() {
//        return null;
//    }
//
//    @Override
//    public ResultSet get() {
//        return null;
//    }
//
//    @Override
//    public void set(String something, String where) {
//        try {
//            Statement stmt = databaseConn.createStatement();
//            stmt.executeUpdate("insert into number_repository (" + where + ") values ('" + something +"')");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public void set(String something1, String something2, String something3) {
//
//    }
//
//    public void addIdAndTableNumber(String id, String tableNumber){
//        try {
//            Statement stmt = databaseConn.createStatement();
//            stmt.executeUpdate("insert into order_repository (id, table_number) values ('" + id + "', '" + tableNumber + "')");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
