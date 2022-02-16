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

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", children=" + children + ", birthday=" + birthday.getTime() + '}';
    }

    public static void main(String[] args) {
        User firstUser = new User("Ivan", 2, new GregorianCalendar(1990, Calendar.FEBRUARY, 20));
        User secondUser = new User("Ivan", 2, new GregorianCalendar(1990, Calendar.FEBRUARY, 20));

        Map<User, Object> map = new HashMap<>();
        map.put(firstUser, new Object());
        map.put(secondUser, new Object());
        System.out.println(map);
    }
}
