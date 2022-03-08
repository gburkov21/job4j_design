package ru.job4j.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
        if (argsName.size() != 4) {
            throw new IllegalArgumentException("Incorrect arguments");
        }
        String sourcePath = argsName.get("path");
        if (!Paths.get(sourcePath).toFile().isFile()) {
            throw new IllegalArgumentException("Parameter 'path' is not file");
        }
        String targetPath = argsName.get("out");
        boolean isStdout = STDOUT.equals(targetPath);
        if (!isStdout && !Paths.get(targetPath).toFile().isFile()) {
            throw new IllegalArgumentException("Parameter 'out' is not file");
        }
        String delimiter = argsName.get("delimiter");
        String outColumns = argsName.get("filter");

        String[] fields = outColumns.split(",");

        try (Scanner scanner = new Scanner(new FileReader(sourcePath, StandardCharsets.UTF_8));
             PrintWriter writer = new PrintWriter(new FileWriter(targetPath, StandardCharsets.UTF_8))) {
            List<String> csvHeaders = List.of(scanner.nextLine().split(delimiter));

            if (isStdout) {
                System.out.println(String.join(delimiter, fields));
            } else {
                writer.write(String.join(delimiter, fields));
                writer.write(System.lineSeparator());
            }

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
                if (isStdout) {
                    System.out.println(String.join(delimiter, values));
                } else {
                    writer.write(String.join(delimiter, values));
                    writer.write(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String sourcePath = "./csv/source.csv";
        String targetPath = "./csv/target.csv";

        ArgsName csvToCsvArgs = ArgsName.of(new String[]{
                "-path=" + sourcePath, "-delimiter=;", "-out=" + targetPath, "-filter=name,age"
        });
        CSVReader.handle(csvToCsvArgs);

        ArgsName csvToConsoleArgs = ArgsName.of(new String[]{
                "-path=" + sourcePath, "-delimiter=;", "-out=" + STDOUT, "-filter=age,education"
        });
        CSVReader.handle(csvToConsoleArgs);
    }
}
