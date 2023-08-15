package org.example.Telegram.KeyBoard.InLine;

import org.example.ClientDataBase.Categories;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

public class InLineKeyboardButtonKitchenCategory extends InLineKeyboardButton {

    private Categories categories = new Categories();
    public InLineKeyboardButtonKitchenCategory() {
        this.hashMapArrayList = categories.getAllCategories();
    }


}
