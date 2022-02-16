package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User firstUser = new User("Ivan", 2, new GregorianCalendar(1990, Calendar.FEBRUARY, 20));
        User secondUser = new User("Ivan", 2, new GregorianCalendar(1990, Calendar.FEBRUARY, 20));

        Map<User, Object> map = new HashMap<>();
        map.put(firstUser, new Object());
        map.put(secondUser, new Object());
    }
}
