package io.github.samnegri.util;

import com.google.gson.Gson;

public class Json {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }
}