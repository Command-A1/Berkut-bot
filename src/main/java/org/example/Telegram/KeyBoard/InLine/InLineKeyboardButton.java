package org.example.Telegram.KeyBoard.InLine;

import org.example.Telegram.LibraryDB.OrderUser;
import org.example.Telegram.Models.Client;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;

public abstract class InLineKeyboardButton {
    protected ArrayList<Map<Integer, String>> hashMapArrayList;
    protected InlineKeyboardMarkup markupInLine;
    protected List<List<InlineKeyboardButton>> rowsInLine;
    protected List<InlineKeyboardButton> rowInLine;


    public void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }

    public SendMessage listCategory(Client client, String step) {

        initializationInlineKeyboard();

        checkStep(step,client);

        for (Map.Entry<Integer, String> entry : hashMapArrayList.get(client.getWhereToWalk()).entrySet()) {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText(entry.getValue());
            rowInLine.get(0).setCallbackData(entry.getKey().toString());

            rowsInLine.add(rowInLine);
            rowInLine = new ArrayList<>();
        }

        addHelpButton(client);
        markupInLine.setKeyboard(rowsInLine);
        client.setReplyMarkupSendMessage(markupInLine);
        return client.getSendMessage();
    }


    public void checkStep(String step,Client client) {
        switch (step) {
            case "след":
                client.setWhereToWalk(1);
                break;
            case "пред":
                client.setWhereToWalk(-1);
                break;
            case "Добавить в заказ":
                client.getOrderUser().addCountDishInOrderUser(client.getWhereToWalk());
                break;
            case "удалить":
                client.getOrderUser().removeCountDishInOrderUser(client.getWhereToWalk());
                break;
        }
    }
    public void addButtonStepPrevious(int number) {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(number).setText(Emoji.ARROW_LEFT.get());
        rowInLine.get(number).setCallbackData("пред");
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

    public void addButtonAddToOrder(int number,Client client) {

        rowInLine.add(new InlineKeyboardButton());
        if (client.getOrderUser().getMapOrderUser().containsKey(client.getWhereToWalk()) && !client.getOrderUser().getMapOrderUser().get(client.getWhereToWalk()).equals(0))
            rowInLine.get(number).setText(Emoji.WHITE_CHECK_MARK.get() + " — " + client.getOrderUser().getMapOrderUser().get(client.getWhereToWalk()));
        else rowInLine.get(number).setText(Emoji.WHITE_CHECK_MARK.get());
        rowInLine.get(number).setCallbackData("Добавить в заказ");
    }

    public void addButtonStepNext(int number) {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(number).setText(Emoji.ARROW_RIGHT.get());
        rowInLine.get(number).setCallbackData("след");
    }


    public void addHelpButton(Client client) {
        if (client.getWhereToWalk() != 0 && client.getWhereToWalk() != hashMapArrayList.size() - 1) {
            addButtonStepPrevious(0);
            addButtonStepNext(1);
            rowsInLine.add(rowInLine);
        } else if (client.getWhereToWalk() == 0) {
            addButtonStepNext(0);
            rowsInLine.add(rowInLine);
        } else {
            addButtonStepPrevious(0);
            rowsInLine.add(rowInLine);
        }
    }

}
