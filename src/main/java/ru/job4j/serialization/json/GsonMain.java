package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonMain {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        Address address = new Address("Moscow", "Pushkina", 33, 57);
        User user = new User(false, 35, "Ivan", address, new String[]{"fishing, hunting"});

        System.out.println(gson.toJson(user));

        String userGson =
                "{"
                        + "\"isMarried\":true,"
                        + "\"age\":40,"
                        + "\"name\":\"Vasya\","
                        + "\"address\":"
                        + "{"
                        + "\"city\":\"Moscow\","
                        + "\"street\":\"Petrova\","
                        + "\"houseNumber\":15,"
                        + "\"apartmentNumber\":20"
                        + "},"
                        + "\"hobbies\":"
                        + "[\"music, drawing\"]"
                        + "}";

        User userFromGson = gson.fromJson(userGson, User.class);
        System.out.println(userFromGson);
    }
}
