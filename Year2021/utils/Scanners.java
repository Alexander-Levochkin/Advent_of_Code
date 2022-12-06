package Year2021.utils;

import Year2021.utils.task4classes.Board;
import Year2021.utils.task4classes.Num;
import Year2021.utils.task5classes.Point;
import Year2021.utils.task5classes.Vent;
import helper.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

/**
 * Class was used to scan the inputs for all the tasks, but was badly designed: it breaks the SOLID principles.
 * The class logic was moved to package "scanner", which uses the Strategy design pattern.
 * @deprecated
 * @see Year2021.utils.scanner.InputScanner
 */
final class Scanners {

    public static String getPath(String classname) {
        return "%s\\%s\\%s.%s".formatted(Constants.PATH, "Year2021", classname, Constants.FILE_TYPE);
    }

    //Bingo
    public static List<Integer> scan4Numbers(String classname) throws IOException {
        Path path = Path.of(getPath(classname));
        try (Scanner scanner = new Scanner(path)) {
            return scanner.findAll("\\d+(?=,)|(?<=,)\\d+").mapToInt(
                    value -> Integer.parseInt(value.group())).boxed().collect(Collectors.toList());
        }
    }

    public static List<Board> scan4Boards(String classname) throws IOException {
        Path path = Path.of(getPath(classname));
        try (Scanner scanner = new Scanner(path)) {
            List<Board> boards = new ArrayList<>();
            for (MatchResult matchResult : scanner.findAll("(( *\\d+ +){4}\\d+\n){5}").toList())
                boards.add(new Board(Arrays.stream(matchResult.group().replaceAll(" (\\d) ", "$1 ").
                        split(" {1,2}|\n")).map(str -> new Num(Integer.parseInt(str))).toList()));
            return boards;
        }
    }

    //Vents
    public static List<Vent> scan5(String classname) throws IOException {
        Path path = Path.of(getPath(classname));
        try (Scanner scanner = new Scanner(path)) {
            return scanner.findAll("\\d+,\\d+ -> \\d+,\\d+").map(value -> {
                String s = value.group().replaceAll(",|( -> )", " ");
                String[] vals = s.split(" ", 4);
                return new Vent(new Point(Integer.parseInt(vals[0]), Integer.parseInt(vals[1])),
                        new Point(Integer.parseInt(vals[2]), Integer.parseInt(vals[3])));
            }).toList();
        }
    }

    //Lanternfish
    public static long[] scan6(String classname) throws IOException {
        Path path = Path.of(getPath(classname));
        long[] result = new long[10];
        for (String line : Files.readAllLines(path)) {
            for (String s : line.split(",")) {
                //increment the amount of times the same Lanternfish appears
                result[Integer.parseInt(s)]++;
            }
        }
        return result;
    }

    //Crabs
    public static List<Integer> scan7(String classname) throws IOException {
        Path path = Path.of(getPath(classname));
        List<Integer> result = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            for (String s : line.split(",")) {
                result.add(Integer.parseInt(s));
            }
        }
        return result;
    }

    //seven-segment displays
    public static List<Integer> scan8(String classname) throws IOException {
        Path path = Path.of(getPath(classname));
        List<Integer> result = new ArrayList<>();
        for (String line : Files.readAllLines(path)) {
            for (String s : line.split(" ")) {
                result.add(Integer.parseInt(s));
            }
        }
        return result;
    }
}
