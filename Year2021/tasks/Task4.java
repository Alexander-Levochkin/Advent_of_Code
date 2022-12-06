package Year2021.tasks;

import Year2021.utils.scanner.Task4Scanner;
import Year2021.utils.scanner.TaskScanner;
import Year2021.utils.task4classes.Board;

import java.io.IOException;
import java.util.List;

/**
 * <a href="https://adventofcode.com/2021/day/4">Task description</a>
 */
public class Task4 {
    private final List<Integer> numbers;
    private final List<Board> boards;

    public Task4() throws IOException {
        Task4Scanner scanner = new Task4Scanner();
        numbers = TaskScanner.scan(scanner, getClass().getSimpleName());
        boards = TaskScanner.scan2(scanner, getClass().getSimpleName());
    }

    public int part1() {
        for (int number : numbers) {
            boards.forEach(board -> board.apply(number));
            Board board;
            if ((board = getWinnerBoard()) != null)
                return board.calculateScore(number);
        }

        return -1;
    }

    public int part2() {
        for (int number : numbers) {
            List<Board> boardsThatDidntWinYet = boardsThatDidntWinYet();
            boards.forEach(board -> board.apply(number));
            if (amountOfBoardsThatWon() == boards.size())
                return boardsThatDidntWinYet.get(0).calculateScore(number);
        }

        return -1;
    }

    private List<Board> boardsThatDidntWinYet() {
        return boards.stream().filter(board -> !board.isWon()).toList();
    }

    private Board getWinnerBoard() {
        return boards.stream().filter(Board::isWon).findFirst().orElse(null);
    }

    private int amountOfBoardsThatWon() {
        return (int) boards.stream().filter(Board::isWon).count();
    }
}
