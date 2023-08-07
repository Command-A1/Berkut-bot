package org.example.ClientDataBase;

import java.util.Scanner;

public class DishMenu {
    static Scanner scanner = new Scanner(System.in);
    public void mainMenu(){
        System.out.println("1.Mеню");
        System.out.println("2.Оформить заказ");
        System.out.println("3.Выход");

        while (true){
            int choice = scanner.nextInt();

            switch (choice)  {
                case (1):
                    menu();
                    break;
                case (2):
                    break;
                case (3):
                    return;
                default:
                    correctInput();
                    break;
            }
        }
    }

    private  void menu() {
    }

    public void correctInput(){
        System.out.println("Выбран неверный пункт меню");
    }

}
