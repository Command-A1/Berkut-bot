package org.example.Telegram.KeyBoard.InLine;

import org.example.Telegram.LibraryDB.OrderUser;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class InLineKeyboardForOrder extends InLineKeyboardButton {
    private OrderUser orderUser;

    public InLineKeyboardForOrder(OrderUser orderUser) {
        this.orderUser = orderUser;
    }

    public SendMessage createMessageOrderAll(SendMessage message) {
        initializationInlineKeyboard();
        message.setText(orderUser.setTextOrderAll());
        setButtonClearAllOrder();
        setButtonConfirm();
        setReplyMarkupMessage();
        message.setReplyMarkup(markupInLine);
        return message;
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
