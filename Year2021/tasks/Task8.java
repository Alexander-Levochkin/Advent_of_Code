package Year2021.tasks;

import Year2021.utils.scanner.Task8Scanner;
import Year2021.utils.scanner.TaskScanner;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * part2 of this task was half copied from the internet but adapted & refactored
 * <p>
 * <a href="https://adventofcode.com/2021/day/8">Task description</a>
 */
public class Task8 {
    private final List<String[]> inputs;
    private final List<String[]> outputs;

    private final List<Integer> uniqueAmountsOfLight = List.of(2, 3, 4, 7);

    public Task8() throws IOException {
        Task8Scanner scanner = new Task8Scanner();
        inputs = TaskScanner.scan(scanner, getClass().getSimpleName());
        outputs = TaskScanner.scan2(scanner, getClass().getSimpleName());
    }

    public int part1() {
        return (int) outputs.stream().flatMap(Arrays::stream)
                .filter(string -> uniqueAmountsOfLight.contains(string.length())).count();
    }

    public int part2() {
        int sum = 0;

        for (int i = 0; i < inputs.size(); i++) {
            String[] pattern = inputs.get(i);
            Map<Integer, String> numberMapping = new HashMap<>();

            //put base (unique) cases (2, 3, 4, 7)
            for (String string : pattern)
                if (uniqueAmountsOfLight.contains(string.length()))
                    numberMapping.put(toNumber(string.length()), string);

            //TODO: fix this
            //put complex (not unique) cases (9, 0, 6, 3, 5, 2)
            for (Integer integer : List.of(9, 0, 6, 3, 5, 2))
                numberMapping.put(integer, getValueForPattern(integer, numberMapping, pattern));

            //convert output values to a number
            //and add it to the sum
            String[] output = outputs.get(i);
            StringBuilder sb = new StringBuilder();
            for (String o : output)
                for (var e : numberMapping.entrySet()) {
                    if (toCharacterSet(e.getValue()).equals(toCharacterSet(o))) {
                        sb.append(e.getKey());
                        break;
                    }
                }
            sum += Integer.parseInt(sb.toString());
        }

        return sum;
    }

    private int toNumber(int stringLength) {
        return switch (stringLength) {
            case 2 -> 1;
            case 4 -> 4;
            case 3 -> 7;
            case 7 -> 8;
            default -> throw new IllegalStateException("Unexpected value: " + stringLength);
        };
    }

    private String getValueForPattern(final int num, Map<Integer, String> numberMapping, String[] pattern) {
        return switch (num) {
            case 0 -> {
                for (String element : pattern)
                    if (containsAllChars(element, numberMapping.get(7)) && element.length() == 6 && !element.equals(numberMapping.get(9)))
                        yield element;
                throw new RuntimeException("case 0 failed");
            }
            case 9 -> getString(numberMapping, pattern, 4, 6);
            case 3 -> getString(numberMapping, pattern, 7, 5);
            case 5 -> {
                for (String element : pattern) {
                    if (containsAllChars(numberMapping.get(6), element) && element.length() == 5) {
                        yield element;
                    }
                }
                throw new RuntimeException("case 5 failed");
            }
            case 2 -> getString(numberMapping, pattern, 5);
            case 6 -> getString(numberMapping, pattern, 6);
            default -> throw new IllegalStateException("Unexpected value: " + num);
        };
    }

    private String getString(Map<Integer, String> numberMapping, String[] pattern, int a, int b) {
        for (String element : pattern) {
            if (containsAllChars(element, numberMapping.get(a)) && element.length() == b) {
                return element;
            }
        }
        return null;
    }

    private String getString(Map<Integer, String> numberMapping, String[] pattern, int a) {
        return Arrays.stream(pattern).filter(p -> p.length() == a && !numberMapping.containsValue(p)).findAny().get();
    }

    private Set<Character> toCharacterSet(String s) {
        return s.chars().mapToObj(n -> (char) n).collect(Collectors.toSet());
    }

    private boolean containsAllChars(String container, String str) {
        return toCharacterSet(container).containsAll(toCharacterSet(str));
    }
}
