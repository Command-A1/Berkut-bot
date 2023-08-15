package org.example.Telegram.LibraryDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ListNameDBTable {
    public static HashMap<Integer, String> listNameDBTableMap = new HashMap<>() {
        {
            ArrayList<String> tablesName = new ArrayList<>(Arrays.asList("offerfromthechef",
                    "coldappetizers",
                    "georgiancuisine",
                    "salads",
                    "soups",
                    "steaks",
                    "hotappetizers",
                    "hotdishes",
                    "fishdishes",
                    "barbecue",
                    "dishesonsaj",
                    "tandoor",
                    "garnish",
                    "dessert",
                    "childrensmenu",
                    "winemap"));
            int counter = 0;
            for (String tableName : tablesName) {
                put(counter, tableName);
                counter++;
            }
        }
    };
    public static HashMap<Integer, String> getListNameDBTable(){
        return listNameDBTableMap;
    }
}
