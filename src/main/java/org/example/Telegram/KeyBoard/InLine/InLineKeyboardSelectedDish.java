package org.example.Telegram.KeyBoard.InLine;

import org.example.ClientDataBase.DishDB;
import org.example.Telegram.Models.Client;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class InLineKeyboardSelectedDish extends InLineKeyboardButton {
    private DishDB dishDB;

    public InLineKeyboardSelectedDish(DishDB dishDB) {
        this.dishDB = dishDB;
    }

    public void recordAllDataAboutDishes(String categoryName) {
        dishDB.getAllDataAboutDishes(categoryName);
    }

    public void setIdDish(int idDish, Client client) {
        client.setWhereToWalk(idDish);
    }

    private void recordCorrectDish(Client client) {
        dishDB.recordCorrectDish(client.getWhereToWalk());
    }

    public SendMessage dishFillData(Client client) {
        initializationInlineKeyboard();
        recordCorrectDish(client);
        if (dishDB.getCorrectDish().getComposition() == null) {
            client.setTextInSendMessage("<b>" + "Название: " + "</b>" + dishDB.getCorrectDish().getName() + "\n\n" +
                    "<b>" + "Цена: " + "</b>" + "<b>" + dishDB.getCorrectDish().getDescription() + "/" + dishDB.getCorrectDish().getPrice() + "</b>");
        }else{
            client.setTextInSendMessage("<b>" + "Название: " + "</b>" + dishDB.getCorrectDish().getName() + "\n\n" +
                    "<b>" + "Состав: " + "</b>" + dishDB.getCorrectDish().getComposition() + "\n\n" +
                    "<b>" + "Цена: " + "</b>" + "<b>" + dishDB.getCorrectDish().getDescription() + "/" + dishDB.getCorrectDish().getPrice() + "</b>");

        }
        addHelpButton(client);
        markupInLine.setKeyboard(rowsInLine);
        client.setReplyMarkupSendMessage(markupInLine);
        return client.getSendMessage();

    }

    @Override
    public void addHelpButton(Client client) {
            if (client.getWhereToWalk() != dishDB.getMapDataDishes().entrySet().iterator().next().getKey() && client.getWhereToWalk() != dishDB.getMapDataDishes().lastKey()) {
                addButtonStepPrevious(0);
                addButtonReturnToMenu(1);
                addButtonRemoveOrder(2);
                addButtonAddToOrder(3, client);
                addButtonStepNext(4);
                rowsInLine.add(rowInLine);
            } else if (client.getWhereToWalk() == dishDB.getMapDataDishes().entrySet().iterator().next().getKey()) {
                addButtonReturnToMenu(0);
                addButtonRemoveOrder(1);
                addButtonAddToOrder(2, client);
                if(dishDB.getMapDataDishes().size()!=1)
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
