package Year2022.tasks;

import Year2022.scanner.InputScanner;
import helper.ToListScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/4">Task description</a>
 */
public class Task4 {

    public Task4() {
    }

    public int part1() throws IOException {
        int amountOfAssignments = 0;
        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            String[] parts = line.split(",");
            int[] part1 = Arrays.stream(parts[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] part2 = Arrays.stream(parts[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (isInside(part1, part2) || isInside(part2, part1)) amountOfAssignments++;
        }
        return amountOfAssignments;
    }

    public int part2() throws IOException {
        int amountOfAssignments = 0;
        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            String[] parts = line.split(",");
            int[] part1 = Arrays.stream(parts[0].split("-")).mapToInt(Integer::parseInt).toArray();
            int[] part2 = Arrays.stream(parts[1].split("-")).mapToInt(Integer::parseInt).toArray();
            if (doOverLap(part1, part2)) amountOfAssignments++;
        }
        return amountOfAssignments;
    }

    private boolean doOverLap(int[] part1, int[] part2) {
        if (part1[0] < part2[0]) return part2[0] <= part1[1];
        else if (part1[0] > part2[0]) return part1[0] <= part2[1];
        else return true;
    }

    private boolean isInside(int[] part1, int[] part2) {
        return part1[0] <= part2[0] && part1[1] >= part2[1];
    }
}
