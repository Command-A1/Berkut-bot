package org.example.ClientDataBase;

import java.util.Scanner;

public class Table  {
    private int tableNumber;
    final private int MaxTableNumber = 10;
    public boolean checkTableNumber(int tableNumber) {
        return tableNumber > 0 && tableNumber <= MaxTableNumber;
    }
}
