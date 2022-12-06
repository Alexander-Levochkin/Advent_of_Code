package Year2021;

import Year2021.tasks.*;
import helper.Constants;

import java.io.IOException;

/**
 * The base class for testing the solutions
 * Example code snippet:
 * <p>{@code TaskX task = new TaskX();}
 * <p>{@code System.out.println(task.part1());}
 * <p>{@code System.out.println(task.part2());}
 * @author Alexander Levochkin
 * @since 1.0
 */
public final class Main2021 {
    public static void main(String[] args) throws IOException {
        Task9 task = new Task9();
        System.out.println(task.part1());
        System.out.println(task.part2());
    }
}
