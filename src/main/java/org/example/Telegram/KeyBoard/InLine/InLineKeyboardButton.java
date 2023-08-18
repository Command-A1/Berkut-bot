package org.example.Telegram.KeyBoard.InLine;

import org.example.Telegram.LibraryDB.OrderUser;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

public abstract class InLineKeyboardButton {
    protected ArrayList<Map<Integer, String>> hashMapArrayList;
    protected int id = 0;
    protected InlineKeyboardMarkup markupInLine;
    protected List<List<InlineKeyboardButton>> rowsInLine;
    protected List<InlineKeyboardButton> rowInLine;
    protected OrderUser orderUser;

    public void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }

    public SendMessage listCategory(SendMessage message, String step) {

        initializationInlineKeyboard();

        checkStep(step);

        for (Map.Entry<Integer, String> entry : hashMapArrayList.get(id).entrySet()) {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText(entry.getValue());
            rowInLine.get(0).setCallbackData(entry.getKey().toString());

            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();
        }

        addHelpButton();
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        return message;
    }


    public void checkStep(String step) {
        switch (step) {
            case "след":
                id++;
                break;
            case "пред":
                id--;
                break;
            case "Добавить в заказ":
                orderUser.addCountDishInOrderUser(id);
                break;
            case "удалить":
                orderUser.removeCountDishInOrderUser(id);
                break;
        }
    }

    public void addButtonReturnToMenu(int number) {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(number).setText(Emoji.MENU.get());
        rowInLine.get(number).setCallbackData("Вернуться в Список Блюд");
    }

    public void addButtonRemoveOrder(int number) {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(number).setText(Emoji.X.get());
        rowInLine.get(number).setCallbackData("удалить");

    }

    public void addButtonAddToOrder(int number) {

        rowInLine.add(new InlineKeyboardButton());
        if (orderUser.getMapOrderUser().containsKey(id) && !orderUser.getMapOrderUser().get(id).equals(0))
            rowInLine.get(number).setText(Emoji.WHITE_CHECK_MARK.get() + " — " + orderUser.getMapOrderUser().get(id));
        else rowInLine.get(number).setText(Emoji.WHITE_CHECK_MARK.get());
        rowInLine.get(number).setCallbackData("Добавить в заказ");
    }

    public void addButtonStepPrevious(int number) {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(number).setText(Emoji.ARROW_LEFT.get());
        rowInLine.get(number).setCallbackData("пред");
    }

    public void addButtonStepNext(int number) {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(number).setText(Emoji.ARROW_RIGHT.get());
        rowInLine.get(number).setCallbackData("след");
    }


    public void addHelpButton() {
        if (id != 0 && id != hashMapArrayList.size() - 1) {
            addButtonStepPrevious(0);
            addButtonStepNext(1);
            rowsInLine.add(rowInLine);
        } else if (id == 0) {
            addButtonStepNext(0);
            rowsInLine.add(rowInLine);
        } else {
            addButtonStepPrevious(0);
            rowsInLine.add(rowInLine);
        }
    }

}
