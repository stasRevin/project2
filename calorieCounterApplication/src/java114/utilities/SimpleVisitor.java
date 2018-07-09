package java114.utilities;
import static java.nio.file.FileVisitResult.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.attribute.*;

public class SimpleVisitor extends SimpleFileVisitor<Path> {

    private final Path target;
    private final Path source;

    public SimpleVisitor(Path target, Path source) {

        this.target = target;
        this.source = source;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {

        try {

            Files.copy(source, target, REPLACE_EXISTING);
        } catch (IOException inputOutputException) {

            inputOutputException.printStackTrace();
        }

        return CONTINUE;

    }


}