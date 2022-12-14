package Year2021.tasks;

import Year2021.utils.scanner.InputScanner;
import helper.ToListScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2021/day/3">Task description</a>
 */
public class Task3 {
    private final List<String> input;

    public Task3() throws IOException {
        String path = InputScanner.getPathString(getClass().getSimpleName());
        input = ToListScanner.toString(path);
    }

    public int part1() {
        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();

        int amountOfBits = input.get(0).length();
        for (int i = 0; i < amountOfBits; i++) {
            final int index = i;
            int amountOfOnes = input.stream().mapToInt(str -> Integer.parseInt(String.valueOf(str.charAt(index)))).sum();
            int amountOfZeros = input.size() - amountOfOnes;

            gammaRate.append(amountOfOnes > amountOfZeros ? 1 : 0);
            epsilonRate.append(amountOfOnes > amountOfZeros ? 0 : 1);
        }

        return parse(gammaRate) * parse(epsilonRate);
    }

    public int part2() {
        return parse(getRating(true)) * parse(getRating(false));
    }

    private String getRating(boolean mostCommon) {
        List<String> ainput = new ArrayList<>(input);
        int amountOfBits = ainput.get(0).length();

        for (int i = 0; i < amountOfBits && ainput.size() > 1; i++) {
            final int index = i;
            int amountOfOnes = ainput.stream().mapToInt(str -> Integer.parseInt(String.valueOf(str.charAt(index)))).sum();
            int amountOfZeros = ainput.size() - amountOfOnes;

            if (!mostCommon ^ amountOfZeros <= amountOfOnes) ainput.removeIf(str -> str.charAt(index) == '0');
            else ainput.removeIf(str -> str.charAt(index) == '1');
        }

        assert ainput.size() == 1;
        return ainput.get(0);
    }

    private int parse(StringBuilder stringBuilder) {
        return parse(stringBuilder.toString());
    }

    private int parse(String string) {
        return Integer.parseInt(string, 2);
    }
}
