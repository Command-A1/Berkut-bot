package org.example.Telegram.KeyBoard.InLine;

import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.validation.constraints.Null;
import java.util.*;

public abstract class InLineKeyboardButton {
    public ArrayList<Map<Integer, String>> hashMapArrayList;
    private int numberMenu = 0;
    private InlineKeyboardMarkup markupInLine;
    private List<List<InlineKeyboardButton>> rowsInLine;
    private List<InlineKeyboardButton> rowInLine;

    private void initializationInlineKeyboard() {
        markupInLine = new InlineKeyboardMarkup();
        rowsInLine = new ArrayList<>();
        rowInLine = new ArrayList<>();
    }

    public SendMessage listCategory(SendMessage message, String step) {

        initializationInlineKeyboard();

        checkStep(step);

        for (Map.Entry<Integer, String> entry : hashMapArrayList.get(numberMenu).entrySet()) {
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
        if (step.equals("след"))
            numberMenu++;
        else if (step.equals("пред")) numberMenu--;
    }

    public void addHelpButton() {
        if (numberMenu != 0 && numberMenu != hashMapArrayList.size() - 1) {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText("пред" + Emoji.ARROW_LEFT.get());
            rowInLine.get(0).setCallbackData("пред");
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(1).setText("след" + Emoji.ARROW_RIGHT.get());
            rowInLine.get(1).setCallbackData("след");
            rowsInLine.add(rowInLine);
        } else if (numberMenu == 0) {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText("след" + Emoji.ARROW_RIGHT.get());
            rowInLine.get(0).setCallbackData("след");
            rowsInLine.add(rowInLine);
        } else {
            rowInLine.add(new InlineKeyboardButton());
            rowInLine.get(0).setText("пред" + Emoji.ARROW_LEFT.get());
            rowInLine.get(0).setCallbackData("пред");
            rowsInLine.add(rowInLine);
        }
    }

}
