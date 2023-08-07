package org.example.Telegram;


import org.example.ClientDataBase.UserNumber;
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
    private String textMessage;
    private ReplyKeyBoardUser replyKeyBoardUser;
    private boolean checkNumber = false;
    private UserNumber userNumber = new UserNumber();
    private Contact contactUser;

    public String getBotUsername() {
        return "@BerkutSamarabot";
    }

    public String getBotToken() {
        return "6472689785:AAGAVWTGJz-JUzUNDv4CIUMu4_QkaFjwW-0";
    }


    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {

            Message message = update.getMessage();
            sendMessage.setChatId(update.getMessage().getChatId()); // chatId уникальный номер пользователя

            if (message.hasContact() && userNumber.checkNumber(message.getContact().getPhoneNumber().substring(2))) {
                contactUser = message.getContact();
                checkNumber = true;
                UserNumber.numberMenu();
            }
            if (!checkNumber) {
                sendMessage(Emoji.WELCOME.get());
                sendMessage("Привет, " + update.getMessage().getChat().getFirstName() + ", давай знакомиться!");
                initializationKeyboardGetNumber();
            }

            if (message.hasText()) {
                String messageText = message.getText();//Записываем само сообщение пользователя
                switch (messageText) {
                    case "/start":

                        break;
                    case "Назад":
                        sendMessage("Вернулись...");
                        break;
                    default:
                        sendMessage("Введите ваш номер и приступим к вашему заказу");
                        break;
                }
            }
        }
    }

    private void initializationKeyboardGetNumber() {
        replyKeyBoardUser = new ReplyKeyBoardUser();
        sendMessage.setReplyMarkup(replyKeyBoardUser.keyboardGetNumber());
        sendMessage.setText("Отправь мне свой номер телефона");
        executeMessage(sendMessage);

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
