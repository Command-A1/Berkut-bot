package org.example.Telegram.Models;

import org.example.Telegram.LibraryDB.OrderUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class Client {
    private boolean inCategoryUser = false;
    private boolean inDishesUser = false;
    private boolean inDishUser = false;
    private boolean confirmOrder = false;
    private boolean contact = false;
    private int whereToWalk = 0;
    private SendMessage sendMessage;
    private String firstName;
    private String nameCategoryTableDataBaseChoosingUser;
    private OrderUser orderUser;

    public Client(Update update) {
        orderUser = new OrderUser();
        sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.enableHtml(true);
        firstName = update.getMessage().getChat().getFirstName();
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public int getWhereToWalk() {
        return whereToWalk;
    }

    public void resetValue() {
        whereToWalk = 0;
    }

    public void setWhereToWalk(int whereToWalk) {
        this.whereToWalk += whereToWalk;
    }

    public boolean getConfirmOrder() {
        return confirmOrder;
    }

    public void setConfirmOrder(boolean confirmOrder) {
        this.confirmOrder = confirmOrder;
    }

    public OrderUser getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(OrderUser orderUser) {
        this.orderUser = orderUser;
    }

    public String getNameCategoryTableDataBaseChoosingUser() {
        return nameCategoryTableDataBaseChoosingUser;
    }

    public void setNameCategoryTableDataBaseChoosingUser(String nameCategoryTableDataBaseChoosingUser) {
        this.nameCategoryTableDataBaseChoosingUser = nameCategoryTableDataBaseChoosingUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public void setReplyMarkupSendMessage(ReplyKeyboardMarkup replyKeyboardMarkup) {
        this.sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public void setReplyMarkupSendMessage(InlineKeyboardMarkup markupInLine) {
        this.sendMessage.setReplyMarkup(markupInLine);
    }

    public void setTextInSendMessage(String textMessage) {
        this.sendMessage.setText(textMessage);
    }


    public boolean getInCategoryUser() {
        return inCategoryUser;
    }

    public void setInCategoryUser(boolean inCategoryUser) {
        this.inCategoryUser = inCategoryUser;
    }

    public boolean getInDishesUser() {
        return inDishesUser;
    }

    public void setInDishesUser(boolean inDishesUser) {
        this.inDishesUser = inDishesUser;
    }

    public boolean getInDishUser() {
        return inDishUser;
    }

    public void setInDishUser(boolean inDishUser) {
        this.inDishUser = inDishUser;
    }


}
