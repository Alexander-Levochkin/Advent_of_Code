package tmpYear;

import tmpYear.tasks.*;

import java.io.IOException;

public class MainTmp {
    public static void main(String[] args) throws IOException {
        var task = new Task1();
        System.out.println(task.part1());
        System.out.println(task.part2());
    }
}
