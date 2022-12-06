package Year2021.tasks;

import Year2021.utils.scanner.Task5Scanner;
import Year2021.utils.scanner.TaskScanner;
import Year2021.utils.task5classes.Point;
import Year2021.utils.task5classes.Vent;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2021/day/5">Task description</a>
 */
public class Task5 {
    private final List<Vent> input;

    public Task5() throws IOException {
        input = TaskScanner.scan(new Task5Scanner(), getClass().getSimpleName());
    }

    public int part1() {
        return doTask(false);
    }

    public int part2() {
        return doTask(true);
    }

    private int doTask(boolean handleDiagonal) {
        Set<Point> allPoints = new HashSet<>();
        Set<Point> overlapPoints = new HashSet<>();

        for (Vent vent : input)
            if (vent.isHorizontal()) handleHorizontal(allPoints, overlapPoints, vent);
            else if (vent.isVertical()) handleVertical(allPoints, overlapPoints, vent);
            else if (handleDiagonal) handleDiagonal(allPoints, overlapPoints, vent);

        return overlapPoints.size();
    }

    private void handleHorizontal(Set<Point> allPoints, Set<Point> overlapPoints, Vent vent) {
        for (Integer x : IntStream.rangeClosed(vent.minX(), vent.maxX()).boxed().toList()) {
            Point point = new Point(x, vent.getY());
            if (allPoints.contains(point)) overlapPoints.add(point);
            else allPoints.add(point);
        }
    }

    private void handleVertical(Set<Point> allPoints, Set<Point> overlapPoints, Vent vent) {
        for (Integer y : IntStream.rangeClosed(vent.minY(), vent.maxY()).boxed().toList()) {
            Point point = new Point(vent.getX(), y);
            if (allPoints.contains(point)) overlapPoints.add(point);
            else allPoints.add(point);
        }
    }

    private void handleDiagonal(Set<Point> allPoints, Set<Point> overlapPoints, Vent vent) {
        final List<Integer> xs = IntStream.rangeClosed(vent.minX(), vent.maxX()).boxed().collect(Collectors.toList());
        final List<Integer> ys = IntStream.rangeClosed(vent.minY(), vent.maxY()).boxed().collect(Collectors.toList());
        if (vent.isXReversed()) Collections.reverse(xs);
        if (vent.isYReversed()) Collections.reverse(ys);

        for (int i = 0; i < xs.size(); i++) {
            Point point = new Point(xs.get(i), ys.get(i));
            if (allPoints.contains(point)) overlapPoints.add(point);
            else allPoints.add(point);
        }
    }
}
