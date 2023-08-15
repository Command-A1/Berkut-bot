package org.example.Telegram;


import org.example.ClientDataBase.UserId;
import org.example.ClientDataBase.UserNumber;
import org.example.Telegram.KeyBoard.InLine.InLineKeyBoardListDishes;
import org.example.Telegram.KeyBoard.InLine.InLineKeyboardButtonKitchenCategory;
import org.example.Telegram.KeyBoard.Reply.ReplyKeyBoardGetNumber;
import org.example.Telegram.KeyBoard.Reply.ReplyKeyBoardUserMenu;
import org.example.Telegram.LibraryDB.ListNameDBTable;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    //private long chatId;
    private SendMessage sendMessage = new SendMessage();
    private InLineKeyboardButtonKitchenCategory categoryDishes;
    private Update update;
    private Message message;
    private ReplyKeyBoardGetNumber replyKeyBoardGetNumber;
    private ReplyKeyBoardUserMenu replyKeyBoardUserMenu = new ReplyKeyBoardUserMenu();
    private InLineKeyBoardListDishes replyKeyBoardListDishes;
    private UserNumber userNumber = new UserNumber();
    private UserId userId = new UserId();
    private boolean inCategoryUser = true;
    private String nameCategoryTableDataBaseChoosingUser;

    public String getBotUsername() {
        return "@BerkutSamarabot";
    }

    public String getBotToken() {
        return "6472689785:AAGAVWTGJz-JUzUNDv4CIUMu4_QkaFjwW-0";
    }


    public void onUpdateReceived(Update update) {
        this.update = update;
        if (update.hasMessage()) {

            message = update.getMessage();
            sendMessage.setChatId(update.getMessage().getChatId()); // chatId уникальный номер пользователя

            if (message.hasContact() && userNumber.checkNumber(message.getContact().getPhoneNumber().substring(2))) {
                recordUserIdAndUserNumber();
                initializeKeyboardUserMenu();
            }
            if (!userId.userIdComparison(update.getMessage().getChatId(), userId.getAllId())) {
                creatingMessageUserGreetingRegistration();
            } else if (message.hasText()) {
                switch (message.getText()) {
                    case "/start":
                        sendMessage(Emoji.WELCOME.get());
                        sendMessage("Я вас помню, " + userNumber.getUserName(sendMessage.getChatId()) + " !");
                        initializeKeyboardUserMenu();
                        break;
                    case "Меню \uD83D\uDCCB":
                        initializeInLineKeyboardCategory();
                        break;
                    default:
                        sendMessage("Пожалуйста отправьте ваш номер для регистрации в нашей системе");
                        break;
                }
            }
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("след")) {
                editMessage();
            } else if (update.getCallbackQuery().getData().equals("пред")) {
                editMessage();
            } else if (chooseCategory()) {
                editMessageOnListDishes();
                inCategoryUser = false;
           }
//            else if (!inCategoryUser &&) {
//
//            }
        }


    }

//    private boolean chooseDish() {
//
//    }

    private void outputDishSelected() {

    }

    private boolean chooseCategory() {
        for (Map.Entry entry : ListNameDBTable.getListNameDBTable().entrySet()) {
            if (entry.getKey().equals(Integer.parseInt(update.getCallbackQuery().getData()))) {
                nameCategoryTableDataBaseChoosingUser = (String) entry.getValue();
                return true;
            }
        }
        return false;
    }

    private void editMessageOnListDishes() {
        deleteMessage();
        initializeKeyboardListDishes();
    }

    private void initializeKeyboardListDishes() {
        replyKeyBoardListDishes = new InLineKeyBoardListDishes(nameCategoryTableDataBaseChoosingUser);
        sendMessage.setText("Выберите блюдо " + Emoji.DISH.get());
        executeMessage(replyKeyBoardListDishes.listCategory(sendMessage, " "));
    }

    private void changeKeyboardListDishes() {
        executeMessage(replyKeyBoardListDishes.listCategory(sendMessage, update.getCallbackQuery().getData()));
    }

    private void editMessage() {
        deleteMessage();
        if (inCategoryUser) changeInLineKeyboardCategory();
        else changeKeyboardListDishes();
    }

    private void changeInLineKeyboardCategory() {
        executeMessage(categoryDishes.listCategory(sendMessage, update.getCallbackQuery().getData()));
    }

    private void initializeKeyboardUserMenu() {
        executeMessage(replyKeyBoardUserMenu.keyboardUserMenu(sendMessage));
    }

    private void initializeInLineKeyboardCategory() {
        categoryDishes = new InLineKeyboardButtonKitchenCategory();
        inCategoryUser = true;
        sendMessage.setText("Выберите категорию" + Emoji.MENU.get());
        executeMessage(categoryDishes.listCategory(sendMessage, " "));
    }

    private void recordUserIdAndUserNumber() {
        userNumber.sortNumber(message);
    }

    private void creatingMessageUserGreetingRegistration() {
        sendMessage(Emoji.WELCOME.get());
        sendMessage("Привет, " + update.getMessage().getChat().getFirstName() + ", давай знакомиться!");
        initializeKeyboardGetNumber();
    }

    private void initializeKeyboardGetNumber() {
        replyKeyBoardGetNumber = new ReplyKeyBoardGetNumber();
        executeMessage(replyKeyBoardGetNumber.keyboardGetNumber(sendMessage));
    }

    private void sendMessage(String text) {
        sendMessage.setText(text);
        executeMessage(sendMessage);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Ошибка "+ e.getMessage());
        }
    }

    private void deleteMessage() {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(sendMessage.getChatId());
        deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
