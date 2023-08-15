package org.example.Telegram.KeyBoard.InLine;

import org.example.ClientDataBase.Dish;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InLineKeyboardSelectedDish extends InLineKeyboardButton {
    private Dish dish = new Dish();
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;

    private void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }
//    public SendMessage dishFillData(SendMessage message){
//
//
//    }
}
