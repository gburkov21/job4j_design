package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> log = new ArrayList<>();
            System.out.println("Введите слово или фразу:");
            String input = bufferedReader.readLine();
            log.add("user: " + input);
            while (!OUT.equals(input)) {
                if (STOP.equals(input)) {
                    while (!CONTINUE.equals(input)) {
                        input = bufferedReader.readLine();
                        log.add("user: " + input);
                    }
                }
                List<String> phraseList = readPhrases();
                double index = Math.random() * phraseList.size();
                String botAnswer = phraseList.get((int) index);
                System.out.println("bot: " + botAnswer);
                log.add("bot: " + botAnswer);
                input = bufferedReader.readLine();
                log.add("user: " + input);
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            for (String answer : log) {
                writer.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./console_bot/log.txt", "./console_bot/answers.txt");
        cc.run();
    }
}
