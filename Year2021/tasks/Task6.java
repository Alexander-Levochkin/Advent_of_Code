package Year2021.tasks;

import Year2021.utils.scanner.Task6Scanner;
import Year2021.utils.scanner.TaskScanner;

import java.io.IOException;
import java.util.Arrays;

/**
 * <a href="https://adventofcode.com/2021/day/6">Task description</a>
 */
public class Task6 {
    private long[] fish;

    public long part1() throws IOException {
        fish = TaskScanner.scan(new Task6Scanner(), getClass().getSimpleName());
        return doTask(80);
    }

    public long part2() throws IOException {
        fish = TaskScanner.scan(new Task6Scanner(), getClass().getSimpleName());
        return doTask(256);
    }

    private long doTask(int days) {
        for (int day = 0; day < days; day++) {
            fish[7] += fish[0]; //fishes with lvl 0 all become fishes with lvl 7
            fish[9] = fish[0]; //new fishes get lvl 9 (7 + 2)
            for (int i = 0; i < 9; i++) {
                fish[i] = fish[i+1]; //decrement lvl of all fishes
            }
            fish[9] = 0; //all fish[9] were already written to fish[8] in the loop above, so we get rid of it
        }

        return Arrays.stream(fish).sum();
    }
}
