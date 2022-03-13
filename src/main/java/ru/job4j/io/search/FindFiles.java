package ru.job4j.io.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FindFiles {
    public static void searchPaths(ArgsName argsName) throws IOException {
        checkParams(argsName);
        String directoryName = argsName.get("d");
        Path directory = Paths.get(directoryName);
        String maskOrFileNameOrRegex = argsName.get("n");
        String searchType = argsName.get("t");
        String fileResult = argsName.get("o");

        List<Path> pathsList = switch (searchType) {
            case "mask" -> {
                String regex = maskOrFileNameOrRegex.replace("?", ".?").replace("*", ".*?");
                yield Search.search(directory, p -> p.toFile().getName().matches(regex));
            }
            case "name" -> Search.search(directory, p -> p.toFile().getName().equalsIgnoreCase(maskOrFileNameOrRegex));
            case "regex" -> Search.search(directory, p -> p.toFile().getName().matches(maskOrFileNameOrRegex));
            default -> new ArrayList<>();
        };
        writeResultsToFile(fileResult, pathsList);
    }

    private static void checkParams(ArgsName args) {
        if (args.size() != 4) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
        Path source = Paths.get(args.get("d"));
        if (!source.toFile().isDirectory()) {
            throw new IllegalArgumentException("Param '-d' is not directory. Example: 'C:/'");
        }
        Path target = Paths.get(args.get("o"));
        if (!target.toFile().exists()) {
            throw new IllegalArgumentException("File from parameter '-o' not exist.");
        }
        if (!target.toFile().isFile()) {
            throw new IllegalArgumentException("File from parameter '-o' not valid. Example: 'log.txt'");
        }
        if (args.get("t").equals("mask") && args.get("n").startsWith("*") && !args.get("n").startsWith("*.")) {
            throw new IllegalArgumentException("Incorrect file mask. Examples: '*.txt', '*.xlsx'");
        }
    }

    private static void writeResultsToFile(String fileResult, List<Path> pathsList) {
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter(fileResult, StandardCharsets.UTF_8))) {
            for (Path path : pathsList) {
                fileWriter.write(path.toAbsolutePath().toString());
                fileWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsNames = ArgsName.of(args);
        searchPaths(argsNames);
    }
}
