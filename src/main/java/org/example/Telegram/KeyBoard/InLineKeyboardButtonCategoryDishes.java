package org.example.Telegram.KeyBoard;

import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class InLineKeyboardButtonCategoryDishes {
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;


    private void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }

    public SendMessage listCategoryDishes(SendMessage message) {
        message.setText("Выберите категорию блюд" + Emoji.MENU.get());
        initializationInlineKeyboard();

        if (true) {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText("Горячие закуски" + Emoji.FIRE.get()+"         "+Emoji.ARROW_RIGHT.get());
            rowInLine.get(0).setCallbackData(Emoji.FIRE.get());

            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();

            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText("Салаты" + Emoji.SALAD.get()+"         "+Emoji.ARROW_RIGHT.get());
            rowInLine.get(0).setCallbackData(Emoji.FIRE.get());

            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();

            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText("Винная карта" + Emoji.WINE.get()+"         "+Emoji.ARROW_RIGHT.get());
            rowInLine.get(0).setCallbackData(Emoji.FIRE.get());

            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();
        }
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        return message;
    }

}
