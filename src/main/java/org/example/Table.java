package org.example;

import java.util.Scanner;

public class Table implements TableUtil {
    private int tableNumber;
    final private int MaxTableNumber = 10;

    @Override
    public int askTableNumber() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Введите номер столика");
            this.tableNumber = scanner.nextInt();

            if (checkTableNumber(this.tableNumber)) break;

            System.out.println("Такого столика нет, пожалуйста повторите ввод");
        } while (true);
        scanner.close();

        return this.tableNumber;
    }

    @Override
    public boolean checkTableNumber(int tableNumber) {
        return tableNumber > 0 && tableNumber <= MaxTableNumber;
    }
}
