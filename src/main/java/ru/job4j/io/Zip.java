package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toAbsolutePath().toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        if (args.length != 3) {
            throw new IllegalArgumentException("There should be three parameters.");
        }
        String pathFolder = argsName.get("d");
        String archiveName = argsName.get("o");
        String excludeExtension = argsName.get("e");
        if (!Paths.get(pathFolder).toFile().isDirectory()) {
            throw new IllegalArgumentException("It is not directory");
        }
        if (!excludeExtension.startsWith(".")) {
            throw new IllegalArgumentException("Wrong extension format");
        }
        List<Path> filteredPaths = Search.search(Paths.get(pathFolder), path -> !path.toFile().getName().endsWith(excludeExtension));
        Zip zip = new Zip();
        zip.packFiles(filteredPaths, new File(archiveName));
    }
}
