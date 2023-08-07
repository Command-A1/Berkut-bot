package org.example.Telegram.KeyBoard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyBoardUser {
    private ReplyKeyboardMarkup keyboardMarkup;
    private List<KeyboardRow> keyboardRowsVertical;

    private KeyboardRow rowHorizontal;

    public ReplyKeyboard keyboardGetNumber() {
        keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardRowsVertical = new ArrayList<>();

        rowHorizontal = new KeyboardRow();
        KeyboardButton e = new KeyboardButton("Отправьте номер");
        e.setRequestContact(true);
        rowHorizontal.add(e);

        keyboardRowsVertical.add(rowHorizontal);

        keyboardMarkup.setKeyboard(keyboardRowsVertical);


        return keyboardMarkup;
    }
}
