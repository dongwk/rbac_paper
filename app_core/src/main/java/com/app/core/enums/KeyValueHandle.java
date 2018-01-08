package com.app.core.enums;

import java.util.ArrayList;
import java.util.List;

public class KeyValueHandle {
    public static  <T extends KeyValueEnum> T get(Class<T> t, Object key){
        if (!t.isEnum()) throw new RuntimeException("t don't is Enum");
        if (key == null) return null;
        T[] ary = t.getEnumConstants();
        if (ary == null || ary.length < 1) return null;

        for (T e: ary) {
            System.out.println(e.getKey());
            if (key.equals(e.getKey())){
                return e;
            }
        }
        return null;
    }

    public static  <T extends KeyValueEnum> List<T> get(Class<T> t, List<?> keys){
        List<T> list = new ArrayList<>(keys.size());
        if (!t.isEnum()) throw new RuntimeException("t don't is Enum");
        if (keys == null) return list;
        T[] ary = t.getEnumConstants();
        if (ary == null || ary.length < 1) return list;

        for (T e: ary) {
            for (Object key : keys) {
                if (key.equals(e.getKey())){
                    list.add(e);
                }
            }
        }
        return list;
    }

}
