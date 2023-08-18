package org.example.Telegram;


import org.example.ClientDataBase.Dish;
import org.example.ClientDataBase.OrderDB;
import org.example.ClientDataBase.UserId;
import org.example.ClientDataBase.UserNumber;
import org.example.Telegram.KeyBoard.InLine.InLineKeyBoardListDishes;
import org.example.Telegram.KeyBoard.InLine.InLineKeyboardButtonKitchenCategory;
import org.example.Telegram.KeyBoard.InLine.InLineKeyboardForOrder;
import org.example.Telegram.KeyBoard.InLine.InLineKeyboardSelectedDish;
import org.example.Telegram.KeyBoard.Reply.ReplyKeyBoardGetNumber;
import org.example.Telegram.KeyBoard.Reply.ReplyKeyBoardUserMenu;
import org.example.Telegram.LibraryDB.ListNameDBTable;
import org.example.Telegram.LibraryDB.OrderUser;
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
    private Dish dish = new Dish();
    private Message message;
    private OrderUser orderUser = new OrderUser(dish);
    private OrderDB orderDB = new OrderDB();
    private InLineKeyboardForOrder inLineKeyboardForOrder = new InLineKeyboardForOrder(orderUser);
    private ReplyKeyBoardGetNumber replyKeyBoardGetNumber;
    private InLineKeyboardSelectedDish inLineKeyboardSelectedDish = new InLineKeyboardSelectedDish(orderUser, dish);

    private ReplyKeyBoardUserMenu replyKeyBoardUserMenu = new ReplyKeyBoardUserMenu();
    private InLineKeyBoardListDishes replyKeyBoardListDishes;
    private UserNumber userNumber = new UserNumber();
    private UserId userId = new UserId();
    private boolean inCategoryUser = true;
    private boolean inDishesUser = false;
    private boolean inDishUser = false;
    private boolean confirmOrder = false;
    private int numberTableUser;
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
            sendMessage.setChatId(update.getMessage().getChatId());

            if (confirmOrder&&message.hasText()) numberTableUser= Integer.parseInt(message.getText());


            if (message.hasContact() && userNumber.checkNumber(message.getContact().getPhoneNumber().substring(1))) {
                recordUserIdAndUserNumber();
                initializeKeyboardUserMenu();
            }
            if (!userId.userIdComparison(update.getMessage().getChatId(), userId.getAllId())) {
                creatingMessageUserGreetingRegistration();
            }
            else if (message.hasText()) {
                switch (message.getText()) {
                    case "/start":
                        sendMessageOnlyText(Emoji.WELCOME.get());
                        sendMessageOnlyText("Я вас помню, " + userNumber.getUserName(sendMessage.getChatId()) + " !");
                        initializeKeyboardUserMenu();
                        break;
                    case "Меню \uD83D\uDCCB":
                        initializeInLineKeyboardCategory();
                        break;
                    case "Заказ \uD83D\uDCDD":
                        sendOrderToUser();
                        break;
                    default:
                        sendMessageOnlyText("Не понимаю о чем ВЫ. Воспользуйтесь кнопочками");
                        break;
                }
            }
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("след")) {
                editMessage();
            } else if (update.getCallbackQuery().getData().equals("пред")) {
                editMessage();
            } else if (inCategoryUser) {
                chooseCategory();
                outputMessageOnListDishes();
                inCategoryUser = false;
                inDishesUser = true;
            } else if (inDishesUser) {
                inDishesUser = false;
                inDishUser = true;
                initializeOutputDishSelected();
            } else if (inDishUser && update.getCallbackQuery().getData().equals("Вернуться в Список Блюд")) {
                inDishesUser = true;
                inDishUser = false;
                outputMessageOnListDishes();
            } else if (inDishUser && update.getCallbackQuery().getData().equals("Добавить в заказ")) {
                deleteMessage();
                editMessageOutputDishSelected();
            } else if (update.getCallbackQuery().getData().equals("удалить")) {
                deleteMessage();
                editMessageOutputDishSelected();
            }else if (update.getCallbackQuery().getData().equals("очистить")) {
                orderUser.clearAllOrder();
                deleteMessage();
                sendOrderToUser();
            } else if (update.getCallbackQuery().getData().equals("оформить")) {
                if (!orderUser.getMapOrderUser().isEmpty()) {
                    confirmOrder = true;
                    sendOrderToBD();
                } else sendMessageOnlyText("Ваш заказ - Пуст. Сначала заполните его! ");
            }
        }


    }

    private void sendOrderToBD() {

    }

    private void sendOrderToUser() {
        executeMessage(inLineKeyboardForOrder.createMessageOrderAll(sendMessage));
    }

    private void initializeOutputDishSelected() {
        deleteMessage();
        inLineKeyboardSelectedDish.recordAllDataAboutDishes(nameCategoryTableDataBaseChoosingUser);
        inLineKeyboardSelectedDish.setIdDish(Integer.parseInt(update.getCallbackQuery().getData()));
        executeMessage(inLineKeyboardSelectedDish.dishFillData(sendMessage));
    }

    private void editMessageOutputDishSelected() {
        inLineKeyboardSelectedDish.checkStep(update.getCallbackQuery().getData());
        executeMessage(inLineKeyboardSelectedDish.dishFillData(sendMessage));
    }

    private void chooseCategory() {
        for (Map.Entry entry : ListNameDBTable.getListNameDBTable().entrySet()) {
            if (entry.getKey().equals(Integer.parseInt(update.getCallbackQuery().getData()))) {
                nameCategoryTableDataBaseChoosingUser = (String) entry.getValue();
            }
        }
    }

    private void outputMessageOnListDishes() {
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
        else if (inDishesUser) changeKeyboardListDishes();
        else editMessageOutputDishSelected();
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
        sendMessageOnlyText(Emoji.WELCOME.get());
        sendMessageOnlyText("Привет, " + update.getMessage().getChat().getFirstName() + ", давай знакомиться!");
        initializeKeyboardGetNumber();
    }

    private void initializeKeyboardGetNumber() {
        replyKeyBoardGetNumber = new ReplyKeyBoardGetNumber();
        executeMessage(replyKeyBoardGetNumber.keyboardGetNumber(sendMessage));
    }

    private void sendMessageOnlyText(String text) {
        SendMessage e = new SendMessage();
        e.setText(text);
        e.setChatId(sendMessage.getChatId());
        executeMessage(e);
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Ошибка " + e.getMessage());
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
