package org.example.Telegram.KeyBoard.InLine;

import org.example.ClientDataBase.Dish;
import org.example.Telegram.LibraryDB.OrderUser;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InLineKeyboardSelectedDish extends InLineKeyboardButton {
    private Dish dish;

    public InLineKeyboardSelectedDish(OrderUser orderUser,Dish dish) {
        this.orderUser = orderUser;
        this.dish=dish;
    }

    public void recordAllDataAboutDishes(String categoryName) {
        dish.getAllDataAboutDishes(categoryName);
    }

    public void setIdDish(int idDish) {
        this.id = idDish;
    }

    private void recordCorrectDish() {
        dish.recordCorrectDish(id);
    }

    public SendMessage dishFillData(SendMessage message) {
        initializationInlineKeyboard();
        recordCorrectDish();
        message.enableHtml(true);
        message.setText("<b>" + "Название: " + "</b>" + dish.getCorrectDish().get(0) + "\n\n" +
                "<b>" + "Состав: " + "</b>" + dish.getCorrectDish().get(2) + "\n\n" +
                "<b>" + "Цена: " + "</b>" + "<b>" + dish.getCorrectDish().get(1) + "/" + dish.getCorrectDish().get(4) + "</b>");
        addHelpButton();
        markupInLine.setKeyboard(rowsInLine);
        message.setReplyMarkup(markupInLine);
        return message;

    }

    @Override
    public void addHelpButton() {
        if (id != dish.getMapDataDishes().entrySet().iterator().next().getKey() && id != dish.getMapDataDishes().lastKey()) {
            addButtonStepPrevious(0);
            addButtonReturnToMenu(1);
            addButtonRemoveOrder(2);
            addButtonAddToOrder(3);
            addButtonStepNext(4);
            rowsInLine.add(rowInLine);
        } else if (id == dish.getMapDataDishes().entrySet().iterator().next().getKey()) {
            addButtonReturnToMenu(0);
            addButtonRemoveOrder(1);
            addButtonAddToOrder(2);
            addButtonStepNext(3);
            rowsInLine.add(rowInLine);
        } else {
            addButtonStepPrevious(0);
            addButtonReturnToMenu(1);
            addButtonRemoveOrder(2);
            addButtonAddToOrder(3);
            rowsInLine.add(rowInLine);
        }
    }
}
