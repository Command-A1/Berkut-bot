package org.example.Telegram.KeyBoard.Reply;

import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyBoardUserMenu {
    private ReplyKeyboardMarkup keyboardMarkup;
    private List<KeyboardRow> keyboardRowsVertical;
    private KeyboardButton button;

    private KeyboardRow rowHorizontal;

    public SendMessage keyboardUserMenu(SendMessage message) {
        keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardRowsVertical = new ArrayList<>();
        message.setText("Приступим к заказу!");
        rowHorizontal = new KeyboardRow();
        button = new KeyboardButton("Меню " + Emoji.MENU.get());
        rowHorizontal.add(button);
        button = new KeyboardButton("Заказ " + Emoji.ORDER_LIST.get());
        rowHorizontal.add(button);

        keyboardRowsVertical.add(rowHorizontal);

        keyboardMarkup.setKeyboard(keyboardRowsVertical);
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }
}
