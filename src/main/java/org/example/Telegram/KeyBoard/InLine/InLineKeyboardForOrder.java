package org.example.Telegram.KeyBoard.InLine;

import org.example.ClientDataBase.Dish;
import org.example.Telegram.Models.Client;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InLineKeyboardForOrder extends InLineKeyboardButton {
    private Dish dish;

    public InLineKeyboardForOrder(Dish dish) {
        this.dish = dish;
    }

    public SendMessage createMessageOrderAll(Client client) {
        initializationInlineKeyboard();
        client.getSendMessage().setText(client.getOrderUser().getTextOrderAll(dish));
        if (!client.getOrderUser().checkOnEmptyMapOrderUser()) {
            setButtonClearAllOrder();
            setButtonConfirm();
        }
        setReplyMarkupMessage();
        client.setReplyMarkupSendMessage(markupInLine);
        return client.getSendMessage();
    }

    private void setButtonClearAllOrder() {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(0).setText("Очистить заказ" + Emoji.X.get());
        rowInLine.get(0).setCallbackData("очистить");
    }

    private void setButtonConfirm() {
        rowInLine.add(new InlineKeyboardButton());
        rowInLine.get(1).setText("Оформить заказ" + Emoji.WHITE_CHECK_MARK.get());
        rowInLine.get(1).setCallbackData("оформить");
    }

    private void setReplyMarkupMessage() {
        rowsInLine.add(rowInLine);
        markupInLine.setKeyboard(rowsInLine);
    }
}
