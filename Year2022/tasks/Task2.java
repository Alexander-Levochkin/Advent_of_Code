package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;

/**
 * <a href="https://adventofcode.com/2022/day/2">Task description</a>
 */
public class Task2 {

    public Task2() {
    }

    public int part1() throws IOException {
        //player 1 (opponent)
        final char ROCK1 = 'A';
        final char PAPER1 = 'B';
        final char SCISSORS1 = 'C';

        //player 2 (me)
        final char ROCK2 = 'X';
        final char PAPER2 = 'Y';
        final char SCISSORS2 = 'Z';

        //scores
        final int winScore = 6;
        final int drawScore = 3;
        final int loseScore = 0;
        final int rockScore = 1;
        final int paperScore = 2;
        final int scissorsScore = 3;

        int totalScore = 0;
        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            char[] chars = String.join("", line.split(" ")).toCharArray();
            totalScore += switch (chars[1]) {
                case ROCK2 -> switch (chars[0]) {
                    case ROCK1 -> drawScore;
                    case PAPER1 -> loseScore;
                    case SCISSORS1 -> winScore;
                    default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
                } + rockScore;
                case PAPER2 -> switch (chars[0]) {
                    case ROCK1 -> winScore;
                    case PAPER1 -> drawScore;
                    case SCISSORS1 -> loseScore;
                    default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
                } + paperScore;
                case SCISSORS2 -> switch (chars[0]) {
                    case ROCK1 -> loseScore;
                    case PAPER1 -> winScore;
                    case SCISSORS1 -> drawScore;
                    default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
                } + scissorsScore;
                default -> throw new IllegalStateException("Unexpected value: " + chars[1]);
            };
        }
        return totalScore;
    }

    public int part2() throws IOException {
        //player 1 (opponent)
        final char ROCK = 'A';
        final char PAPER = 'B';
        final char SCISSORS = 'C';

        //strategies
        final char LOSE = 'X';
        final char DRAW = 'Y';
        final char WIN = 'Z';

        //scores
        final int winScore = 6;
        final int drawScore = 3;
        final int loseScore = 0;
        final int rockScore = 1;
        final int paperScore = 2;
        final int scissorsScore = 3;

        int totalScore = 0;
        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            char[] chars = String.join("", line.split(" ")).toCharArray();
            totalScore += switch (chars[1]) {
                case LOSE -> switch (chars[0]) {
                    case ROCK -> scissorsScore;
                    case PAPER -> rockScore;
                    case SCISSORS -> paperScore;
                    default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
                } + loseScore;
                case DRAW -> switch (chars[0]) {
                    case ROCK -> rockScore;
                    case PAPER -> paperScore;
                    case SCISSORS -> scissorsScore;
                    default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
                } + drawScore;
                case WIN -> switch (chars[0]) {
                    case ROCK -> paperScore;
                    case PAPER -> scissorsScore;
                    case SCISSORS -> rockScore;
                    default -> throw new IllegalStateException("Unexpected value: " + chars[0]);
                } + winScore;
                default -> throw new IllegalStateException("Unexpected value: " + chars[1]);
            };
        }
        return totalScore;
    }
}
