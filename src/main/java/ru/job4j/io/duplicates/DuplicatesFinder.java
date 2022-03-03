package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), visitor);
        visitor.getPathMap().values()
                .stream()
                .filter(paths -> paths.size() > 1)
                .flatMap(Collection::stream)
                .forEach(path -> System.out.println(path.toAbsolutePath()));
    }
}
