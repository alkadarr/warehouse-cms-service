package com.radev.basecode.common;

import java.util.HashMap;
import java.util.Map;

public class GlobalCache {

    public static Map<String,String> cache = new HashMap<>();

    // Get All data in global cache variable
    public static Map<String,String> getAll() {
        return cache;
    }

    // Get Data in global cache variable
    public static String get(String key){
        return cache.get(key);
    }

    // Put Data in global cache variable
    public static void put(String key, String value){
        cache.put(key,value);
    }
}
