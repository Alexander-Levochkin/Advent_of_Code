package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://adventofcode.com/2022/day/5">Task description</a>
 */
public class Task5 {
    private final List<String> input;

    public Task5() throws IOException {
        input = Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()));
    }

    public String part1() throws IOException {
        List<List<Character>> containers = readContainers();
        List<String> moves = input.subList(10, input.size());

        for (String move : moves) {
            Scanner scanner = new Scanner(move);
            int[] list = scanner.findAll("\\d+").mapToInt(value -> Integer.parseInt(value.group())).toArray();
            scanner.close();
            move9000(containers, list[0], list[1]-1, list[2]-1);
        }

        return getTopContainers(containers);
    }

    public String part2() {
        List<List<Character>> containers = readContainers();
        List<String> moves = input.subList(10, input.size());

        for (String move : moves) {
            Scanner scanner = new Scanner(move);
            int[] list = scanner.findAll("\\d+").mapToInt(value -> Integer.parseInt(value.group())).toArray();
            scanner.close();
            move9001(containers, list[0], list[1]-1, list[2]-1);
        }

        return getTopContainers(containers);
    }

    private String getTopContainers(List<List<Character>> containers) {
        return containers.stream().map(chars -> chars.isEmpty() ? "" : String.valueOf(chars.get(0))).collect(Collectors.joining());
    }

    private List<List<Character>> readContainers() {
        List<List<Character>> result = new ArrayList<>();
        for (int i = 0; i < 9; ++i) result.add(new ArrayList<>());

        List<String> rawContainers = input.subList(0, 8);
        for (String line : rawContainers) {
            int j = 0;
            for (int i = 1; i < 38 && i < line.length(); i += 4, ++j) {
                char c = line.charAt(i);
                if (c != ' ') result.get(j).add(c);
            }
        }

        return result;
    }

    public void move9000(List<List<Character>> containers, int amount, int fromIndex, int toIndex) {
        for (int i = 0; i < amount; i++) containers.get(toIndex).add(0, containers.get(fromIndex).remove(0));
    }

    public void move9001(List<List<Character>> containers, int amount, int fromIndex, int toIndex) {
        List<Character> toAdd = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) toAdd.add(containers.get(fromIndex).remove(0));
        containers.get(toIndex).addAll(0, toAdd);
    }
}
