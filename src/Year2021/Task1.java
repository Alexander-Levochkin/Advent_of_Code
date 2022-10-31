package Year2021;

import helper.Strings;
import helper.ToListScanner;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Task1 {
    private final List<Integer> input;

    public Task1() throws IOException {
        String path = Strings.get(getClass().getSimpleName());
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
