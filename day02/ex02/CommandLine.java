import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class CommandLine {
    private Path currentDir;

    public CommandLine(Path dir) {
        this.currentDir = dir;
    }

    public Path getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(Path currentDir) {
        this.currentDir = currentDir;
    }

    public void ls() throws IOException {
        Stream<Path> stream = Files.walk(this.getCurrentDir(), 1);
        List<Path> paths = stream.collect(Collectors.toList());
        for (Path p : paths) {
            if (p.equals(currentDir) || p.getFileName().toString().startsWith(".")) {
                continue;
            }
            System.out.println(p.getFileName() + " " + Files.size(p) + " KB");
        }
    }

    public void cd(Path dest) throws IOException {
        Path absolutePath = dest;
        if (!dest.isAbsolute()) {
            absolutePath = Paths.get(this.getCurrentDir().toString() + "/" + dest.toString());
        }
        if (Files.exists(absolutePath) && Files.isDirectory(absolutePath)) {
            this.setCurrentDir(absolutePath.normalize());
            this.pwd();
        }
        else {
            System.out.println("Can't change to " + dest);
        }
    }

    public void mv(Path source, Path dest) throws IOException {
        Path absoluteSrs = Paths.get(this.getCurrentDir() + "/" + source).normalize();
        Path absoluteDest = Paths.get(this.getCurrentDir() + "/" + dest).normalize();
        if (Files.isRegularFile(absoluteSrs)) {
            if (Files.isDirectory(absoluteDest))
                absoluteDest = Paths.get(absoluteDest + "/" + absoluteSrs.getFileName());
            Files.move(absoluteSrs, absoluteDest, REPLACE_EXISTING);
        }
        else {
            System.out.println("Wrong source file path");
        }
    }

    public void pwd() throws IOException {
        System.out.println(this.getCurrentDir());
    }
}
