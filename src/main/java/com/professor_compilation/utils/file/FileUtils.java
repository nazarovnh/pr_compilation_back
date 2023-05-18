package com.professor_compilation.utils.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * class FileUtils
 */
public class FileUtils {

    public static void saveFilesLocally(final MultipartFile sourceCode, final String path, final String fileName) throws IOException {
        Path pathToFiles = generatePath(path, fileName);
        Files.write(pathToFiles, sourceCode.getBytes());
    }

    public static Path generatePath(final String path, final String fileName) {
        return Paths.get(path + "/" + fileName);
    }

    public static void copyFileInDirectory(final String filePath, final String dir) throws IOException {
        Path source = Paths.get(filePath);
        Path target = Paths.get(dir);
        Files.copy(source, target, REPLACE_EXISTING);
    }

    public static void deleteDirectory(String dir) throws IOException {
        Path path = Paths.get(dir);

        try (Stream<Path> walk = Files.walk(path)) {
            walk
                    .sorted(Comparator.reverseOrder())
                    .forEach(FileUtils::deleteByPath);
        }

    }

    public static void deleteByPath(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.printf("Unable to delete this path : %s%n%s", path, e);
        }
    }
}
