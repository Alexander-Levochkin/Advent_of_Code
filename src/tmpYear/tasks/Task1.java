package tmpYear.tasks;

import helper.ToListScanner;
import tmpYear.MainTmp;

import java.io.IOException;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/1">Task description</a>
 */
public class Task1 {
    private final List<Integer> input;

    public Task1() throws IOException {
        String path = getClass().getSimpleName();
        input = ToListScanner.toInteger(path);
    }

    public int part1() {
        return -1;
    }

    public int part2() {
        return -1;
    }
}
