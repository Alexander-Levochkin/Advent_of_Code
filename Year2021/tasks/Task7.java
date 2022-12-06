package Year2021.tasks;

import Year2021.utils.scanner.Task7Scanner;
import Year2021.utils.scanner.TaskScanner;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2021/day/7">Task description</a>
 */
public class Task7 {
    private final List<Integer> positions;

    public Task7() throws IOException {
        positions = TaskScanner.scan(new Task7Scanner(), getClass().getSimpleName());
    }

    public int part1() {
        int minFuel = Integer.MAX_VALUE;
        for (int i = 0; i < positions.size(); i++) {
            int fuel = 0;
            for (int j = 0; j < positions.size(); j++) {
                if (i != j) fuel += Math.abs(positions.get(j) - positions.get(i));
            }
            if (fuel < minFuel) minFuel = fuel;
        }
        return minFuel;
    }

    public int part2() {
        int minFuel = Integer.MAX_VALUE;
        //TODO change name of "position"
        for (int position = 0; position < positions.stream().max(Integer::compareTo).orElse(-1); position++) {
            int fuel = getFuel(position);
            if (fuel < minFuel) minFuel = fuel;
        }
        return minFuel;
    }

    private int getFuel(Integer middlePosition) {
        int fuel = 0;
        for (Integer position : positions)
            fuel += IntStream.rangeClosed(1, Math.abs(middlePosition - position)).sum();
        return fuel;
    }

/*    public int part2() {
//        List<Integer> input = List.of(16,1,2,0,4,2,7,1,2,14);
        final int average = (int) Math.round(input.stream().mapToInt(Integer::intValue).average().orElse(-1));
        assert average != -1;
        System.out.println("average = " + average);

        int minFuel = 0;
        for (Integer integer : input) {
            for (int i = 1; i <= Math.abs(integer - average); i++) {
                minFuel += i;
            }
        }
        return minFuel;
    }*/
}
