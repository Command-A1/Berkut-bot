package org.example.Telegram.KeyBoard.Reply;

import org.example.Telegram.Models.Client;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyBoardUserMenu extends ReplyKeyBoardButton{

    public SendMessage keyboardUserMenu(Client client, String text) {
        initializeObjectInlineKeyboard();
        client.setTextInSendMessage(text);
        rowHorizontal.add(new KeyboardButton("Меню " + Emoji.MENU.get()));
        rowHorizontal.add(new KeyboardButton("Заказ " + Emoji.ORDER_LIST.get()));

        keyboardRowsVertical.add(rowHorizontal);

        keyboardMarkup.setKeyboard(keyboardRowsVertical);
        client.setReplyMarkupSendMessage(keyboardMarkup);

        return client.getSendMessage();
    }
}
