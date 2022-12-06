package Year2022.tasks;

import Year2022.scanner.InputScanner;
import Year2022.scanner.Task3Scanner;
import Year2022.scanner.TaskScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2022/day/3">Task description</a>
 */
public class Task3 {

    public Task3() {
    }

    public int part1() throws IOException {
        int sumOfPriorities = 0;
        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            int middle = line.length() / 2;
            String compartment1 = line.substring(0, middle);
            String compartment2 = line.substring(middle);
            sumOfPriorities += getEqualChar(compartment1, compartment2);
        }
        return sumOfPriorities;
    }

    public int part2() throws IOException {
        int sumOfPriorities = 0;
        for (String[] group : TaskScanner.scan(new Task3Scanner(), getClass().getSimpleName())) {
            sumOfPriorities += getEqualChar(getEqualChars(group[0], group[1]), group[2]);
        }
        return sumOfPriorities;
    }

    private int getEqualChar(List<Character> chars, String s2) {
        for (char character : chars)
            if (s2.indexOf(character) != -1) return Character.isUpperCase(character) ? (int) character - 38 : (int) character - 96;
        throw new IllegalArgumentException("Non chars match");
    }

    private int getEqualChar(String s1, String s2) {
        for (char character : s1.toCharArray())
            if (s2.indexOf(character) != -1) return Character.isUpperCase(character) ? (int) character - 38 : (int) character - 96;
        throw new IllegalArgumentException("Non chars match");
    }

    private List<Character> getEqualChars(String s1, String s2) {
        List<Character> result = new ArrayList<>();
        for (char character : s1.toCharArray())
            if (s2.indexOf(character) != -1) result.add(character);
        return result;
    }
}
