package com.codecool.wishit.utilities;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONParser {

    public static  <T> T parsToObject(String json, String key, Class<T> classOfT) {
        // RECEIVE JSON AND CONVERT IT TO ADDRESS
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Gson gson = new Gson();
        JsonElement originJson = jsonObject.get(key);
        return gson.fromJson(originJson, classOfT);
    }
}
