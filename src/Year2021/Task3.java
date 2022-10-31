package Year2021;

import helper.Strings;
import helper.ToListScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    private final List<String> input;

    public Task3() throws IOException {
        String path = Strings.get(getClass().getSimpleName());
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
        System.out.println();
        return parse(getOxygenGeneratorRating()) * parse(getCO2ScrubberRating());
    }

    private String getOxygenGeneratorRating() {
        List<String> ainput = new ArrayList<>(input);
        int amountOfBits = ainput.get(0).length();

        for (int i = 0; i < amountOfBits && ainput.size() > 1; i++) {
            final int index = i;
            int amountOfOnes = ainput.stream().mapToInt(str -> Integer.parseInt(String.valueOf(str.charAt(index)))).sum();
            int amountOfZeros = ainput.size() - amountOfOnes;

            //oxygen
            if (amountOfZeros <= amountOfOnes) ainput.removeIf(str -> str.charAt(index) == '0');
            else ainput.removeIf(str -> str.charAt(index) == '1');
        }

        assert ainput.size() == 1;
        return ainput.get(0);
    }

    private String getCO2ScrubberRating() {
        List<String> ainput = new ArrayList<>(input);
        int amountOfBits = ainput.get(0).length();

        for (int i = 0; i < amountOfBits && ainput.size() > 1; i++) {
            final int index = i;
            int amountOfOnes = ainput.stream().mapToInt(str -> Integer.parseInt(String.valueOf(str.charAt(index)))).sum();
            int amountOfZeros = ainput.size() - amountOfOnes;

            //CO2
            if (amountOfZeros <= amountOfOnes) ainput.removeIf(str -> str.charAt(index) == '1');
            else ainput.removeIf(str -> str.charAt(index) == '0');
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
