package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://adventofcode.com/2022/day/9">Task description</a>
 */
public class Task9 {

    public int part1() throws IOException {
        Rope rope = new Rope(2);
        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            String[] args = line.split(" ");
            for (int i = 0; i < Integer.parseInt(args[1]); i++)
                rope.move(args[0]);
        }
        return rope.getTail().markedCells.size();
    }

    public int part2() throws IOException {
        Rope rope = new Rope(10);
        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            String[] args = line.split(" ");
            for (int i = 0; i < Integer.parseInt(args[1]); i++)
                rope.move(args[0]);
        }
        return rope.getTail().markedCells.size();
    }

    private static class Rope {
        int xHead = 1;
        int yHead = 1;
        Rope tail;
        final Set<Coordinate> markedCells = new HashSet<>();

        public Rope(int size) {
            Rope current = this;
            for (int i = 0; i < size-1; i++) {
                Rope rope = new Rope();
                current.setTail(rope);
                current = rope;
            }
            current.setTail(null);
        }

        private Rope() {
            markedCells.add(new Coordinate(1, 1));
        }

        public void setTail(Rope tail) {
            this.tail = tail;
        }

        public void move(String direction) {
            moveHead(direction);
            moveTail();
        }

        private void moveTail() {
            if (tail != null) {
                boolean moveX = Math.abs(xHead - tail.xHead) == 2;
                boolean moveY = Math.abs(yHead - tail.yHead) == 2;

                if ((moveX && Math.abs(yHead - tail.yHead) == 1) || (moveY && Math.abs(xHead - tail.xHead) == 1)) {
                    tail.xHead += xHead < tail.xHead ? -1 : 1;
                    tail.yHead += yHead < tail.yHead ? -1 : 1;
                } else if (moveX && !moveY) tail.xHead += xHead < tail.xHead ? -1 : 1;
                else if (!moveX && moveY) tail.yHead += yHead < tail.yHead ? -1 : 1;

                tail.markedCells.add(new Coordinate(tail.xHead, tail.yHead));
                tail.moveTail();
            }
        }

        private void moveHead(String direction) {
            switch (direction) {
                case "L" -> xHead--;
                case "R" -> xHead++;
                case "U" -> yHead++;
                case "D" -> yHead--;
            }
            markedCells.add(new Coordinate(xHead, yHead));
        }

        public Rope getTail() {
            return tail == null ? this : tail.getTail();
        }

        private record Coordinate(int x, int y) {}
    }
}
