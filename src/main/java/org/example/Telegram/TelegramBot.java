package org.example.Telegram;


import org.example.ClientDataBase.UserNumber;
import org.example.Telegram.KeyBoard.InLineKeyboardButtonCategoryDishes;
import org.example.Telegram.KeyBoard.ReplyKeyBoardUser;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    //private long chatId;
    private SendMessage sendMessage = new SendMessage();
    private InLineKeyboardButtonCategoryDishes categoryDishes = new InLineKeyboardButtonCategoryDishes();
    private Update update;
    private Message message;
    private ReplyKeyBoardUser replyKeyBoardUser = new ReplyKeyBoardUser();
    private UserNumber userNumber = new UserNumber();
    private Contact contactUser;

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
                initializeInLineKeyboardCategory();

            }
            if (!userNumber.userIdComparison(update.getMessage().getChatId(), userNumber.getAllId())) {
                creatingMessageUserGreetingRegistration();
            } else {
                switch (message.getText()) {
                    case "/start":

                        sendMessage(Emoji.WELCOME.get());
                        sendMessage("Я вас помню, " + userNumber.getUserName(sendMessage.getChatId()) + " !");

                        break;
                    case "Назад":
                        sendMessage("Вернулись...");
                        break;
                    default:
                        sendMessage("Пожалуйста отправьте ваш номер для регистрации в нашей системе");
                        break;
                }
            }
        }
    }

    private void initializeInLineKeyboardCategory() {
        executeMessage(categoryDishes.listCategoryDishes(sendMessage));
    }

    private void recordUserIdAndUserNumber() {
        contactUser = message.getContact();
        userNumber.sortNumber(message);
    }

    private void creatingMessageUserGreetingRegistration() {
        sendMessage(Emoji.WELCOME.get());
        sendMessage("Привет, " + update.getMessage().getChat().getFirstName() + ", давай знакомиться!");
        initializeKeyboardGetNumber();
    }

    private void initializeKeyboardGetNumber() {
        executeMessage(replyKeyBoardUser.keyboardGetNumber(sendMessage));
    }

    private void sendMessage(String text) {
        sendMessage.setText(text);
        executeMessage(sendMessage);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
        }
    }
}
