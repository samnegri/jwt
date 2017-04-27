package io.github.samnegri.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Json {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static Map fromJson(String json) {
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        Map<String, String> map = new Gson().fromJson(json, type);
        return map;
    }
}
