package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static final String STDOUT = "stdout";

    public static void handle(ArgsName argsName) throws Exception {
        checkParams(argsName);
        String sourcePath = argsName.get("path");
        String targetPath = argsName.get("out");
        boolean isStdout = STDOUT.equals(targetPath);
        String delimiter = argsName.get("delimiter");
        String outColumns = argsName.get("filter");

        String[] fields = outColumns.split(",");

        try (Scanner scanner = new Scanner(new FileReader(sourcePath, StandardCharsets.UTF_8))) {
            List<String> csvHeaders = List.of(scanner.nextLine().split(delimiter));

            StringBuilder builder = new StringBuilder();
            builder.append(String.join(delimiter, fields).concat(System.lineSeparator()));

            List<Integer> indexes = new ArrayList<>();
            for (String field : fields) {
                int fieldIndex = csvHeaders.indexOf(field);
                if (fieldIndex != -1) {
                    indexes.add(fieldIndex);
                }
            }
            while (scanner.hasNext()) {
                String[] csvRow = scanner.nextLine().split(delimiter);
                List<String> values = new ArrayList<>();
                for (int index : indexes) {
                    values.add(csvRow[index]);
                }
                builder.append(String.join(delimiter, values).concat(System.lineSeparator()));
            }

            if (isStdout) {
                System.out.println(builder);
            } else {
                try (PrintWriter fileWriter = new PrintWriter(new FileWriter(targetPath, StandardCharsets.UTF_8))) {
                    fileWriter.write(String.valueOf(builder));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkParams(ArgsName args) {
        if (args.size() != 4) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
        if (!Paths.get(args.get("path")).toFile().isFile()) {
            throw new IllegalArgumentException("Parameter 'path' is not file");
        }
        String targetPath = args.get("out");
        if (!STDOUT.equals(targetPath) && !Paths.get(targetPath).toFile().isFile()) {
            throw new IllegalArgumentException("Parameter 'out' is not file");
        }
        if (args.get("delimiter") == null) {
            throw new IllegalArgumentException("Delimiter is null");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsNames = ArgsName.of(args);
        handle(argsNames);
    }
}
