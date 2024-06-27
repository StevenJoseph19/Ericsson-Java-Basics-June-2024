package com.mycompany.util;

import java.util.*;

public class CollectionsClasses {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("List Elements:" + list);

        Set set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);

        System.out.println("Set Elements:" + set);

        Map map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Eve", 22);

        System.out.println("Map Elements:" + map);
    }
}
