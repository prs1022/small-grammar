package com.fsm.fsmdemo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rensong.pu
 * @date 2025/8/7
 */
public class StringSet {
    public void add(String str) {
        get(str);
    }

    /**
     * 获取字符串的索引
     * @param string
     * @return
     */
    public int get(String string) {
        Item item = items.get(string);

        if(item == null) {
            item = new Item();
            item.string = string;
            item.index = items.size();
            items.put(string, item);
            strings.add(string);
        }

        return item.index;
    }

    private static class Item {
        String string;
        int index;
    }

    private final Map<String,Item> items = new HashMap<>();
    private final List<String> strings = new ArrayList<>();

}
