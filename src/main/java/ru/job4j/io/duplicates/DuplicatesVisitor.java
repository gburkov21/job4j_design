package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> pathMap = new HashMap<>();

    public Map<FileProperty, List<Path>> getPathMap() {
        return pathMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.toFile().getName());
        pathMap.computeIfPresent(fileProperty, (key, value) -> {
            value.add(file);
            return value;
        });
        pathMap.putIfAbsent(fileProperty, new ArrayList<>(List.of(file)));
        return super.visitFile(file, attrs);
    }
}
