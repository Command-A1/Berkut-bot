package org.example.Telegram.KeyBoard;

import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

    public SendMessage keyboardGetNumber(SendMessage message) {
        keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardRowsVertical = new ArrayList<>();

        rowHorizontal = new KeyboardRow();
        KeyboardButton e = new KeyboardButton("Отправьте номер" + Emoji.TELEPHONE.get());
        e.setRequestContact(true);
        rowHorizontal.add(e);

        keyboardRowsVertical.add(rowHorizontal);

        keyboardMarkup.setKeyboard(keyboardRowsVertical);
        message.setReplyMarkup(keyboardMarkup);
        message.setText("Отправь мне свой номер телефона");

        return message;
    }
}
