package Year2021.utils.scanner;

import Year2021.utils.task4classes.Board;
import Year2021.utils.task4classes.Num;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class Task4Scanner implements InputScanner2<List<Integer>, List<Board>> {
    //Bingo

    //Numbers
    @Override
    public List<Integer> scan(String classname) throws IOException {
        try (Scanner scanner = new Scanner(InputScanner.getPath(classname))) {
            return scanner.findAll("\\d+(?=,)|(?<=,)\\d+").mapToInt(
                    value -> Integer.parseInt(value.group())).boxed().collect(Collectors.toList());
        }
    }

    //Boards
    @Override
    public List<Board> scan2(String classname) throws IOException {
        try (Scanner scanner = new Scanner(InputScanner.getPath(classname))) {
            List<Board> boards = new ArrayList<>();
            for (MatchResult matchResult : scanner.findAll("(( *\\d+ +){4}\\d+\n){5}").toList())
                boards.add(new Board(Arrays.stream(matchResult.group().replaceAll(" (\\d) ", "$1 ").
                        split(" {1,2}|\n")).map(str -> new Num(Integer.parseInt(str))).toList()));
            return boards;
        }
    }
}
