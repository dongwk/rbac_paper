package com;

import com.app.common.util.ValidateUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
//        System.out.println("".split(",").length);

//        System.out.println(new Date().getTime());

//        BigDecimal bigDecimal = new BigDecimal(8.30);
//        System.out.println(bigDecimal.doubleValue());

//        List<String> existList = Lists.newArrayList("1", "1");
//        Set<String> existSet = existList.stream().map(i -> i).collect(Collectors.toSet());
//        System.out.println(existSet);

//        Map<Integer, Integer> map = Maps.newHashMap();
//        map.put(1, 1);
//        map.put(2, 2);
//
//        Set<Integer> set = Sets.newHashSet(11);
//
//        Set<Integer> eset = map.keySet();
//
//        eset.removeAll(set);
//
//        System.out.println(map);

//        System.out.println(ValidateUtil.isChinese("啊"));
        System.out.println(ValidateUtil.isRightfulString("A1-_#"));
    }

}
