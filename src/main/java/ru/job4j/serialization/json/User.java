package ru.job4j.serialization.json;

import java.util.Arrays;

public class User {
    private boolean isMarried;
    private int age;
    private String name;
    private Address address;
    private String[] hobbies;

    public User(boolean isMarried, int age, String name, Address address, String[] hobbies) {
        this.age = age;
        this.name = name;
        this.address = address;
        this.hobbies = hobbies;
        this.isMarried = isMarried;
    }

    @Override
    public String toString() {
        return String.format("User{isMarried=%s, age=%d, name='%s', address=%s, hobbies=%s}", isMarried, age, name, address, Arrays.toString(hobbies));
    }
}
