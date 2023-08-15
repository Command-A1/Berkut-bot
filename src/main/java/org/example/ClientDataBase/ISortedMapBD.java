package org.example.ClientDataBase;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public interface ISortedMapBD {

     default ArrayList<Map<Integer, String>> sortedCategoriesTreeMap(
             TreeMap<Integer, String> treeMap) {
         ArrayList<Map<Integer, String>> hashMapArrayList =new ArrayList<>();
         hashMapArrayList.add(0, new TreeMap<>());
         int c = 0;

         for (Map.Entry entry: treeMap.entrySet()){
             if (hashMapArrayList.get(c).size()==4) {
                 c++;
                 hashMapArrayList.add(c, new TreeMap<>());
             }
             hashMapArrayList.get(c).put((Integer) entry.getKey(), (String) entry.getValue());

         }
         return hashMapArrayList;
    }
}
