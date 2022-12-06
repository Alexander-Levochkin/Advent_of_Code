package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * <a href="https://adventofcode.com/2022/day/6">Task description</a>
 */
public class Task6 {
    private final String input;

    public Task6() throws IOException {
        input = Files.readString(InputScanner.getPath(getClass().getSimpleName()));
    }

    public int part1() {
        return task(4);
    }

    public int part2() {
        return task(14);
    }

    private int task(int amountOfChars) {
        for (int i = 0; i < input.toCharArray().length; i++)
            if (input.substring(i, i + amountOfChars).chars().distinct().count() == amountOfChars)
                return i + amountOfChars;
        return -1;
    }
}
