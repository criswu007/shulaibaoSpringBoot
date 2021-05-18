package com.example.mybatisplus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
//        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        System.out.println(hash(1));
        System.out.println(hash(1));
        map.put(1, 2);
        map.put(1, 3);
        map.put(2, 5);
        map.put(3, 6);
        map.put(3, 7);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    public static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
