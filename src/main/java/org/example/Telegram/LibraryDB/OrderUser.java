package org.example.Telegram.LibraryDB;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.example.ClientDataBase.Dish;
import org.example.Telegram.Models.Emoji;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.HashMap;
import java.util.Map;

public class OrderUser {
    private HashMap<Integer, Integer> mapOrderUser;
    private Dish dish;

    public OrderUser(Dish dish) {
        mapOrderUser = new HashMap<>();
        this.dish = dish;
    }

    public void setMapOrderUser(HashMap<Integer, Integer> mapOrderUser) {
        this.mapOrderUser = mapOrderUser;
    }

    public void addCountDishInOrderUser(int idDish) {
        if (mapOrderUser.containsKey(idDish))
            this.mapOrderUser.put(idDish, mapOrderUser.get(idDish) + 1);
        else
            this.mapOrderUser.put(idDish, 1);
    }

    public void removeCountDishInOrderUser(int idDish) {
        if (!mapOrderUser.isEmpty() && mapOrderUser.containsKey(idDish) && !mapOrderUser.get(idDish).equals(0))
            this.mapOrderUser.put(idDish, mapOrderUser.get(idDish) - 1);
    }

    public HashMap<Integer, Integer> getMapOrderUser() {
        return mapOrderUser;
    }

    public void clearAllOrder() {
        mapOrderUser.clear();
    }

    public String setTextOrderAll() {
        String text = "Ваш заказ: \n\n";
        if (mapOrderUser.isEmpty()) text += "Здесь пока перекати поле. Но мы ждем твой заказ" + Emoji.SMILE_BLUSH.get();
        else {
            for (Map.Entry entry : mapOrderUser.entrySet()) {
                text += dish.getMapDataDishes().get(entry.getKey()).get(0) + " — " + entry.getValue()+ " шт"+ "\n\n";
            }
        }
        return text;
    }
}
