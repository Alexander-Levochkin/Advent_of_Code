package Year2021.tasks;

import Year2021.utils.scanner.InputScanner;
import helper.ToListScanner;

import java.io.IOException;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2021/day/2">Task description</a>
 */
public class Task2 {
    private final List<String> input;

    public Task2() throws IOException {
        String path = InputScanner.getPathString(getClass().getSimpleName());
        input = ToListScanner.toString(path);
    }

    public int part1() {
        int horizontal = 0;
        int vertical = 0;

        for (String line : input) {
            String[] pair = line.split(" ", 2);
            int amount = Integer.parseInt(pair[1]);
            switch (pair[0]) {
                case "forward" -> horizontal += amount;
                case "up" -> vertical -= amount;
                case "down" -> vertical += amount;
            }
        }

        return horizontal * vertical;
    }

    public int part2() {
        int horizontal = 0;
        int vertical = 0;
        int aim = 0;

        for (String line : input) {
            String[] pair = line.split(" ", 2);
            int amount = Integer.parseInt(pair[1]);
            switch (pair[0]) {
                case "forward" -> {
                    horizontal += amount;
                    vertical += aim * amount;
                }
                case "up" -> aim -= amount;
                case "down" -> aim += amount;
            }
        }

        return horizontal * vertical;
    }
}
