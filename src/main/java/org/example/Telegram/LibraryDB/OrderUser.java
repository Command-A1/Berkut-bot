package org.example.Telegram.LibraryDB;

import org.example.ClientDataBase.DishDB;
import org.example.Telegram.Models.Emoji;

import java.util.HashMap;
import java.util.Map;

public class OrderUser {
    private HashMap<Integer, Integer> mapOrderUser;

    public OrderUser() {
        mapOrderUser = new HashMap<>();
    }

    public void addCountDishInOrderUser(int idDish) {
        if (mapOrderUser.containsKey(idDish))
            this.mapOrderUser.put(idDish, mapOrderUser.get(idDish) + 1);
        else
            this.mapOrderUser.put(idDish, 1);
    }

    public void removeCountDishInOrderUser(int idDish) {
        if (!mapOrderUser.isEmpty() && mapOrderUser.containsKey(idDish) && !mapOrderUser.get(idDish).equals(0)) {
            this.mapOrderUser.put(idDish, mapOrderUser.get(idDish) - 1);
            if (mapOrderUser.get(idDish).equals(0)) {
                mapOrderUser.remove(idDish);
            }
        }
    }

    public HashMap<Integer, Integer> getMapOrderUser() {
        return mapOrderUser;
    }

    public void clearAllOrder() {
        mapOrderUser.clear();
    }

    public boolean checkOnEmptyMapOrderUser() {
        return mapOrderUser.isEmpty();
    }

    public String getTextOrderAll(DishDB dishDB) {
        String text = "Ваш заказ: \n\n";
        if (mapOrderUser.isEmpty()) text += "Здесь пока перекати поле. Но мы ждем твой заказ" + Emoji.SMILE_BLUSH.get();
        else {
            for (Map.Entry entry : mapOrderUser.entrySet()) {
                    text += dishDB.getMapDataDishes().get(entry.getKey()).getName() + " — " + entry.getValue() + " шт" + "\n\n";
            }
        }
        return text;
    }
}
