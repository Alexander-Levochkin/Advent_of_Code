package Year2021.tasks;

import Year2021.utils.scanner.InputScanner;
import helper.ToListScanner;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

/**
 *  <a href="https://adventofcode.com/2021/day/1">Task description</a>
 */
public class Task1 {
    private final List<Integer> input;

    public Task1() throws IOException {
        String path = InputScanner.getPathString(getClass().getSimpleName());
        input = ToListScanner.toInteger(path);
    }

    public long part1() {
        return IntStream.range(1, input.size()).filter(i -> input.get(i) > input.get(i - 1)).count();
    }

    public int part2() {
        int count = 0;
        for (int i = 0; i < input.size() - 3; i++) {
            int sum1 = input.get(i) + input.get(i + 1) + input.get(i + 2);
            int sum2 = input.get(i + 1) + input.get(i + 2) + input.get(i + 3);
            if(sum2 > sum1) count++;
        }
        return count;
    }
}
