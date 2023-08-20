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
import org.example.Telegram.Models.Client;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class TelegramBot extends TelegramLongPollingBot {

    private SendMessage sendMessage = new SendMessage();
    private InLineKeyboardButtonKitchenCategory categoryDishes;
    private Update update;
    private Dish dish = new Dish();

    private OrderDB orderDB = new OrderDB();
    private InLineKeyboardForOrder inLineKeyboardForOrder = new InLineKeyboardForOrder(dish);
    private ReplyKeyBoardGetNumber replyKeyBoardGetNumber;
    private InLineKeyboardSelectedDish inLineKeyboardSelectedDish = new InLineKeyboardSelectedDish(dish);

    private ReplyKeyBoardUserMenu replyKeyBoardUserMenu = new ReplyKeyBoardUserMenu();
    private InLineKeyBoardListDishes replyKeyBoardListDishes;
    private UserNumber userNumber = new UserNumber();
    private UserId userId = new UserId();

    private HashMap<Long, ArrayList<Boolean>> localeUserInMenu = new HashMap<>();
    private long chatId;

    private HashMap<Long, Client> mapIdClient = new HashMap<>();

    public String getBotUsername() {
        return "@BerkutSamarabot";
    }

    public String getBotToken() {
        return "6472689785:AAGAVWTGJz-JUzUNDv4CIUMu4_QkaFjwW-0";
    }


    public void onUpdateReceived(Update update) {
        this.update = update;
        if (update.hasMessage()) {
            if (!mapIdClient.containsKey(update.getMessage().getChatId()))
                mapIdClient.put(update.getMessage().getChatId(), new Client(update));


            if (mapIdClient.get(chatId).getConfirmOrder()) sendOrderToBD();

            if (update.getMessage().hasContact()) {
                mapIdClient.get(update.getMessage().getChatId()).setContact(true);
                recordUserIdAndUserNumber();
                initializeKeyboardUserMenu();
            }
            if (!userId.userIdComparison(update.getMessage().getChatId(), userId.getAllId())) {
                creatingMessageUserGreetingRegistration();
            } else if (update.getMessage().hasText()) {
                switch (update.getMessage().getText()) {
                    case "/start":
                        creatingMessageUserRemember();
                        initializeKeyboardUserMenu();
                        break;
                    case "Меню \uD83D\uDCCB":
                        initializeInLineKeyboardCategory();
                        mapIdClient.get(update.getMessage().getChatId()).setInCategoryUser(true);
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
            chatId = update.getCallbackQuery().getMessage().getChatId();
            if (update.getCallbackQuery().getData().equals("след")) {
                editMessage();
            } else if (update.getCallbackQuery().getData().equals("пред")) {
                editMessage();
            } else if (mapIdClient.get(chatId).getInCategoryUser()) {
                chooseCategory();
                outputMessageOnListDishes();
                mapIdClient.get(chatId).setInCategoryUser(false);
                mapIdClient.get(chatId).setInDishesUser(true);
            } else if (mapIdClient.get(chatId).getInDishesUser()) {
                mapIdClient.get(chatId).setInDishesUser(false);
                initializeOutputDishSelected();
            } else if (update.getCallbackQuery().getData().equals("Вернуться в Список Блюд")) {
                mapIdClient.get(chatId).resetValue();
                mapIdClient.get(chatId).setInDishesUser(true);
                outputMessageOnListDishes();
            } else if (update.getCallbackQuery().getData().equals("Добавить в заказ")) {
                deleteMessage();
                editMessageOutputDishSelected();
            } else if (update.getCallbackQuery().getData().equals("удалить")) {
                deleteMessage();
                editMessageOutputDishSelected();
            } else if (update.getCallbackQuery().getData().equals("очистить")) {
                mapIdClient.get(chatId).getOrderUser().clearAllOrder();
                deleteMessage();
                sendOrderToUserInLine();
            } else if (update.getCallbackQuery().getData().equals("оформить")) {
                if (!mapIdClient.get(chatId).getOrderUser().getMapOrderUser().isEmpty()) {
                    mapIdClient.get(chatId).setConfirmOrder(true);

                } else sendMessageOnlyText("Ваш заказ - Пуст. Сначала заполните его! ");
            }
        }


    }

    private void sendOrderToBD() {
        mapIdClient.get(chatId).setConfirmOrder(false);
        
    }
//    private void recordMessageId(){
//
//    }

    private void sendOrderToUser() {
        executeMessage(inLineKeyboardForOrder.createMessageOrderAll(mapIdClient.get(update.getMessage().getChatId())));
    }

    private void sendOrderToUserInLine() {
        executeMessage(inLineKeyboardForOrder.createMessageOrderAll(mapIdClient.get(chatId)));
    }

    private void initializeOutputDishSelected() {
        deleteMessage();
        inLineKeyboardSelectedDish.recordAllDataAboutDishes(mapIdClient.get(chatId).getNameCategoryTableDataBaseChoosingUser());
        inLineKeyboardSelectedDish.setIdDish(Integer.parseInt(update.getCallbackQuery().getData()), mapIdClient.get(chatId));
        executeMessage(inLineKeyboardSelectedDish.dishFillData(mapIdClient.get(chatId)));
    }

    private void editMessageOutputDishSelected() {
        inLineKeyboardSelectedDish.checkStep(update.getCallbackQuery().getData(), mapIdClient.get(chatId));
        executeMessage(inLineKeyboardSelectedDish.dishFillData(mapIdClient.get(chatId)));
    }

    private boolean chooseCategory() {
        for (Map.Entry entry : ListNameDBTable.getListNameDBTable().entrySet()) {
            if (entry.getKey().equals(Integer.parseInt(update.getCallbackQuery().getData()))) {
                mapIdClient.get(chatId).setNameCategoryTableDataBaseChoosingUser((String) entry.getValue());
                return true;
            }
        }
        return false;
    }

    private void outputMessageOnListDishes() {
        deleteMessage();
        initializeKeyboardListDishes();
    }

    private void initializeKeyboardListDishes() {
        replyKeyBoardListDishes = new InLineKeyBoardListDishes(mapIdClient.get(chatId).getNameCategoryTableDataBaseChoosingUser());
        mapIdClient.get(chatId).setTextInSendMessage("Выберите блюдо " + Emoji.DISH.get());
        executeMessage(replyKeyBoardListDishes.listCategory(mapIdClient.get(chatId), " "));
    }

    private void changeKeyboardListDishes() {
        executeMessage(replyKeyBoardListDishes.listCategory(mapIdClient.get(chatId), update.getCallbackQuery().getData()));
    }

    private void editMessage() {
        deleteMessage();
        if (mapIdClient.get(chatId).getInCategoryUser())
            changeInLineKeyboardCategory();
        else if (mapIdClient.get(chatId).getInDishesUser())
            changeKeyboardListDishes();
        else
            editMessageOutputDishSelected();
    }

    private void changeInLineKeyboardCategory() {
        executeMessage(categoryDishes.listCategory(mapIdClient.get(chatId), update.getCallbackQuery().getData()));
    }

    private void initializeKeyboardUserMenu() {
        executeMessage(replyKeyBoardUserMenu.keyboardUserMenu(mapIdClient.get(update.getMessage().getChatId())));
    }

    private void initializeInLineKeyboardCategory() {
        mapIdClient.get(update.getMessage().getChatId()).resetValue();
        categoryDishes = new InLineKeyboardButtonKitchenCategory();
        mapIdClient.get(update.getMessage().getChatId()).setTextInSendMessage("Выберите категорию" + Emoji.MENU.get());
        executeMessage(categoryDishes.listCategory(mapIdClient.get(update.getMessage().getChatId()), " "));
    }

    private void recordUserIdAndUserNumber() {
        userNumber.sortNumber(update.getMessage());
    }

    private void creatingMessageUserGreetingRegistration() {
        sendMessageOnlyText(Emoji.WELCOME.get());
        sendMessageOnlyText("Привет, " + mapIdClient.get(update.getMessage().getChatId()).getFirstName() + ", давай знакомиться!");
        initializeKeyboardGetNumber();
    }

    private void creatingMessageUserRemember() {
        sendMessageOnlyText(Emoji.WELCOME.get());
        sendMessageOnlyText("Я вас помню, " + userNumber.getUserName(String.valueOf(update.getMessage().getChatId())) + " !");

    }

    private void initializeKeyboardGetNumber() {
        replyKeyBoardGetNumber = new ReplyKeyBoardGetNumber();
        executeMessage(replyKeyBoardGetNumber.keyboardGetNumber(mapIdClient.get(update.getMessage().getChatId())));
    }

    private void sendMessageOnlyText(String text) {
        SendMessage e = new SendMessage();
        e.setText(text);
        e.setChatId(mapIdClient.get(update.getMessage().getChatId()).getSendMessage().getChatId());
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
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
