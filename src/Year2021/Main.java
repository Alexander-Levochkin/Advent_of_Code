package Year2021;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Task3 task3 = new Task3();
//        System.out.println(task3.part1());
        int answer = task3.part2();
        int correct = 1613181;
        System.out.println(answer == correct);
        System.out.println("answer = " + answer);
        System.out.println("correct = " + correct);
    }
}
