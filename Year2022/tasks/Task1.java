package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <a href="https://adventofcode.com/2022/day/1">Task description</a>
 */
public class Task1 {

    public Task1() {

    }

    public int part1() throws IOException {
        String path = InputScanner.getPathString(getClass().getSimpleName());
        List<Integer> a = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            int sum = 0;
            for (String calories : lines.toList()) {
                if (calories.isEmpty()) {
                    a.add(sum);
                    sum = 0;
                } else sum += Integer.parseInt(calories);
            }
        }
        return a.stream().max(Integer::compareTo).get();
    }

    public int part2() throws IOException {
        String path = InputScanner.getPathString(getClass().getSimpleName());
        List<Integer> a = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            int sum = 0;
            for (String calories : lines.toList())
                if (calories.isEmpty()) {
                    a.add(sum);
                    sum = 0;
                } else sum += Integer.parseInt(calories);
            a.add(sum);
        }
        Collections.sort(a);
        return a.get(a.size()-1) + a.get(a.size()-2) + a.get(a.size()-3);
    }
}
