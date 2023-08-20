package org.example.Telegram.KeyBoard.Reply;

import org.example.Telegram.Models.Client;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyBoardGetNumber extends ReplyKeyBoardButton {

    public SendMessage keyboardGetNumber(Client client) {
        initializeObjectInlineKeyboard();
        client.setTextInSendMessage("Отправь свой номер");
        KeyboardButton e = new KeyboardButton("Отправить номер" + Emoji.TELEPHONE.get());
        e.setRequestContact(true);
        rowHorizontal.add(e);

        keyboardRowsVertical.add(rowHorizontal);

        keyboardMarkup.setKeyboard(keyboardRowsVertical);
        client.setReplyMarkupSendMessage(keyboardMarkup);

        return client.getSendMessage();
    }
}
