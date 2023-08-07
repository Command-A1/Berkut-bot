package org.example.ClientDataBase;

import java.sql.*;
import java.util.Scanner;

public class UserNumber extends DataBase implements NumberUtil {
    private String clientNumber;

    final private static Connection databaseConn;

    static {
        try {
            databaseConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/menu_db",
                    "postgres","120304");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void numberMenu(){
        DataBase.driverConnections();
        UserNumber number = new UserNumber();
        String clientNumber = number.askNumber();
        if (number.numberComparison(clientNumber, number.get())) number.set(clientNumber, "Number");
    }

    @Override
     public boolean checkNumber(String clientNumber) {
        final String numRegex = "^\\d{10}";
        return this.clientNumber.matches(numRegex);
    }

    @Override
    public String askNumber() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Введите номер телефона");
            System.out.print("+7");
            this.clientNumber = scanner.nextLine();

            if (checkNumber(this.clientNumber)) break;

            System.out.println("Неверный набран номер, пожалуйста повторите ввод");
        } while (true);
        scanner.close();

        return this.clientNumber;
    }

    @Override
    public ResultSet get() {
        try {
            Statement stmt = databaseConn.createStatement();
            return stmt.executeQuery("select number from number_repository");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean numberComparison(String number, ResultSet allNumbers){
        try {
            while (allNumbers.next())
                if (number.equals(allNumbers.getString("number"))) return false;
            return true;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public void set(String number, String where) {
        try {
            Statement stmt = databaseConn.createStatement();
            stmt.executeUpdate("insert into number_repository (" + where + ") values ('" + number +"')");
            databaseConn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
