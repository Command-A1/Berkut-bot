package org.example.Telegram.KeyBoard.InLine;

import org.example.ClientDataBase.Dishes;


public class InLineKeyBoardListDishes extends InLineKeyboardButton {
    private final Dishes dishes = new Dishes();
    public InLineKeyBoardListDishes(String categoryName) {
        this.hashMapArrayList = dishes.getAllCategoriesNameDishes(categoryName);
    }





}
