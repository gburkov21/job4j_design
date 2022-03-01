package ru.job4j.io;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (!line.startsWith("#") && !line.isBlank()) {
                    String[] split = line.split("=", 2);
                    if (split.length < 2 || split[0].isBlank() || split[1].isBlank()) {
                        throw new IllegalArgumentException();
                    }
                    values.put(split[0], split[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    public int size() {
        return values.size();
    }

    public static void main(String[] args) {

    }
}
