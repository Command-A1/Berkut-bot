package org.example.Telegram.KeyBoard.InLine;

import org.example.ClientDataBase.Dish;
import org.example.Telegram.LibraryDB.OrderUser;
import org.example.Telegram.Models.Client;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InLineKeyboardSelectedDish extends InLineKeyboardButton {
    private Dish dish;

    public InLineKeyboardSelectedDish(Dish dish) {
        this.dish = dish;
    }

    public void recordAllDataAboutDishes(String categoryName) {
        dish.getAllDataAboutDishes(categoryName);
    }

    public void setIdDish(int idDish, Client client) {
        client.setWhereToWalk(idDish);
    }

    private void recordCorrectDish(Client client) {
        dish.recordCorrectDish(client.getWhereToWalk());
    }

    public SendMessage dishFillData(Client client) {
        initializationInlineKeyboard();
        recordCorrectDish(client);
        if (dish.getCorrectDish().get(2) == null) {
            client.setTextInSendMessage("<b>" + "Название: " + "</b>" + dish.getCorrectDish().get(0) + "\n\n" +
                    "<b>" + "Цена: " + "</b>" + "<b>" + dish.getCorrectDish().get(1) + "/" + dish.getCorrectDish().get(4) + "</b>");
        }else{
            client.setTextInSendMessage("<b>" + "Название: " + "</b>" + dish.getCorrectDish().get(0) + "\n\n" +
                    "<b>" + "Состав: " + "</b>" + dish.getCorrectDish().get(2) + "\n\n" +
                    "<b>" + "Цена: " + "</b>" + "<b>" + dish.getCorrectDish().get(1) + "/" + dish.getCorrectDish().get(4) + "</b>");

        }
        addHelpButton(client);
        markupInLine.setKeyboard(rowsInLine);
        client.setReplyMarkupSendMessage(markupInLine);
        return client.getSendMessage();

    }

    @Override
    public void addHelpButton(Client client) {
        if (client.getWhereToWalk() != dish.getMapDataDishes().entrySet().iterator().next().getKey() && client.getWhereToWalk() != dish.getMapDataDishes().lastKey()) {
            addButtonStepPrevious(0);
            addButtonReturnToMenu(1);
            addButtonRemoveOrder(2);
            addButtonAddToOrder(3, client);
            addButtonStepNext(4);
            rowsInLine.add(rowInLine);
        } else if (client.getWhereToWalk() == dish.getMapDataDishes().entrySet().iterator().next().getKey()) {
            addButtonReturnToMenu(0);
            addButtonRemoveOrder(1);
            addButtonAddToOrder(2, client);
            addButtonStepNext(3);
            rowsInLine.add(rowInLine);
        } else {
            addButtonStepPrevious(0);
            addButtonReturnToMenu(1);
            addButtonRemoveOrder(2);
            addButtonAddToOrder(3, client);
            rowsInLine.add(rowInLine);
        }
    }
}
