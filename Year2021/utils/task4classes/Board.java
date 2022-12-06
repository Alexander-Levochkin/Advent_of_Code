package Year2021.utils.task4classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    public static final int BOARD_SIZE = 25;
    public static final int BOARD_LENGTH = 5;

    private final List<Num> allNums;
    private final List<List<Num>> rows;
    private final List<List<Num>> columns;

    private boolean isWon = false;

    public Board(List<Num> allNums) {
        assert allNums.size() == BOARD_SIZE;

        this.allNums = Collections.unmodifiableList(allNums);
        rows = Collections.unmodifiableList(toRows(allNums));
        columns = Collections.unmodifiableList(toColumns(allNums));
    }

    private List<List<Num>> toRows(List<Num> allNums) {
        List<List<Num>> rows = new ArrayList<>(BOARD_SIZE / BOARD_LENGTH);
        for (int i = 0; i < BOARD_SIZE; i+=5) {
            rows.add(List.of(allNums.get(i), allNums.get(i+1), allNums.get(i+2), allNums.get(i+3), allNums.get(i+4)));
        }
        return rows;
    }

    private List<List<Num>> toColumns(List<Num> allNums) {
        List<List<Num>> columns = new ArrayList<>(BOARD_SIZE / BOARD_LENGTH);
        for (int i = 0; i < BOARD_LENGTH; i++) {
            columns.add(List.of(allNums.get(i), allNums.get(i+5), allNums.get(i+10), allNums.get(i+15), allNums.get(i+20)));
        }
        return columns;
    }

    public void apply(Integer number) {
        allNums.stream().filter(num -> num.getValue() == number).forEach(Num::setMarked);
        if (contains5MarkedElements(rows) || contains5MarkedElements(columns)) isWon = true;
    }

    private boolean contains5MarkedElements(List<List<Num>> list) {
        return list.stream().anyMatch(nums -> nums.stream().filter(Num::isMarked).count() == BOARD_LENGTH);
    }

    public int calculateScore(int number) {
        return number * allNums.stream().filter(num -> !num.isMarked()).mapToInt(Num::getValue).sum();
    }

    public boolean isWon() {
        return isWon;
    }

    @Override
    public String toString() {
        return "Board{" + allNums + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board board)) return false;

        return allNums.equals(board.allNums);
    }

    @Override
    public int hashCode() {
        return allNums.hashCode();
    }
}
