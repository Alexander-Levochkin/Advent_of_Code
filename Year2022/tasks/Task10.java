package Year2022.tasks;

import Year2022.scanner.InputScanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.StringJoiner;

/**
 * <a href="https://adventofcode.com/2022/day/10">Task description</a>
 */
public class Task10 {

    public int part1() throws IOException {
        int sum = 0;
        int x = 1;
        int cycle = 1;
        List<Integer> wantedCycles = List.of(20, 60, 100, 140, 180, 220);

        for (String line : Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()))) {
            String[] args = line.split(" ");
            if (args.length == 1) cycle++;
            else {
                if (wantedCycles.contains(cycle+1)) sum += (cycle+1) * x;
                cycle += 2;
                x += Integer.parseInt(args[1]);
            }

            if (wantedCycles.contains(cycle)) sum += cycle * x;
        }
        return sum;
    }

    public String part2() throws IOException {
        StringJoiner result = new StringJoiner("\n");

        List<String> lines = Files.readAllLines(InputScanner.getPath(getClass().getSimpleName()));
        int x = 1;
        int cycle = 0;
        int lineIndex = 0;

        for (int i = 1; i <= 6; i++) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int j = 0; cycle < i*40; j++, cycle++, lineIndex++) {
                String[] args = lines.get(lineIndex).split(" ");
                rowBuilder.append(spriteMatches(x, j));
                if (args.length == 2) {
                    if (cycle+1 < i*40) {
                        j++;
                        cycle++;
                        rowBuilder.append(spriteMatches(x, j));
                        x += Integer.parseInt(args[1]);
                    } else {
                        x += Integer.parseInt(args[1]);
                        break;
                    }
                }
            }
            System.out.println(rowBuilder.length());
            result.add(rowBuilder.toString());
        }

        return result.toString();
    }

    private char spriteMatches(int x, int j) {
        return x-1 == j || x == j || x+1 == j ? '#' : '.';
    }
}
