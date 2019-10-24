package com;

import java.util.*;

public class BaseTest {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");

        MyListMap<Integer, String> listMap = toList(map);
        List<Integer> keyList = listMap.byKey();
        List<String> valueList = listMap.byValue();
        System.out.println(keyList);
        System.out.println(valueList);

    }

    static class MyListMap<K,V>{
        private List<K> keyList;
        private List<V> valueList;

        public MyListMap(){}

        public MyListMap(Map<K, V> map){
            Set<K> set = map.keySet();
            keyList = new ArrayList<K>(set);
            Collection<V> values = map.values();
            valueList = new ArrayList<V>(values);
        }

        public List<K> byKey(){
            return keyList;
        }

        public List<V> byValue(){
            return valueList;
        }
    }

    public static <K,V> MyListMap<K,V> toList(Map<K, V> map){
        return new MyListMap(map);
    }

}
