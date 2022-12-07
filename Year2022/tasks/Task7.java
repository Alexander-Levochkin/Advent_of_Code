package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/7">Task description</a>
 */
public class Task7 {
    private static final int DIR_LIMIT = 100_000;
    private static final int CAPACITY = 70_000_000;
    private static final int NEED_SPACE = 30_000_000;

    private final Directory mainDirectory;
    private final List<Directory> directories;

    public Task7() throws IOException {
        List<String> lines = Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()));
        mainDirectory = new Directory("/", null);
        Directory current = mainDirectory;
        for (int i = 1; i < lines.size(); i++) current = handleLine(current, lines.get(i));
        directories = readAllDirectories(mainDirectory);
    }

    public int part1() {
        return directories.stream().mapToInt(Directory::getTotalSize).filter(value -> value < DIR_LIMIT).sum();
    }

    public int part2() {
        int toDelete = NEED_SPACE - (CAPACITY - mainDirectory.getTotalSize());
        return directories.stream().mapToInt(Directory::getTotalSize).filter(value -> value > toDelete).min().orElseThrow();
    }

    private Directory handleLine(Directory current, String line) {
        if (line.equals("$ cd ..")) return current.parent;
        else if (line.startsWith("$ cd")) {
            String newDirName = line.replace("$ cd ", "");
            return current.innerDirectories.stream().filter(directory -> directory.name.equals(newDirName)).findAny().orElseThrow();
        }
        else if (line.startsWith("dir"))
            current.innerDirectories.add(new Directory(line.replace("dir ", ""), current));
        else if (Character.isDigit(line.charAt(0))) current.fileSize += Integer.parseInt(line.replaceAll("\\D", ""));

        return current;
    }

    private List<Directory> readAllDirectories(Directory directory) {
        List<Directory> result = new ArrayList<>();
        for (Directory directory2 : directory.innerDirectories) {
            result.add(directory2);
            result.addAll(readAllDirectories(directory2));
        }
        return result;
    }

    private static class Directory {
        private final String name;
        private final List<Directory> innerDirectories = new ArrayList<>();
        private int fileSize = 0;
        private final Directory parent;

        public Directory(String name, Directory parent) {
            this.name = name;
            this.parent = parent;
        }

        public int getTotalSize() {
            return fileSize + innerDirectories.stream().mapToInt(Directory::getTotalSize).sum();
        }
    }
}
