package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int addCount = 0;
        int deleteCount = 0;
        int changeCount = 0;

        Map<Integer, String> currentMap = current.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName));

        for (User user : previous) {
            int id = user.getId();
            String nameById = currentMap.get(id);
            if (nameById == null) {
                deleteCount++;
            } else if (!nameById.equals(user.getName())) {
                changeCount++;
            }
            currentMap.remove(id);
        }
        addCount = currentMap.size();
        return new Info(addCount, changeCount, deleteCount);
    }
}
