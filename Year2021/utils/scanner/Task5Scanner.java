package Year2021.utils.scanner;

import Year2021.utils.task5classes.Point;
import Year2021.utils.task5classes.Vent;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Task5Scanner implements InputScanner<List<Vent>> {
    //Vents
    @Override
    public List<Vent> scan(String classname) throws IOException {
        try (Scanner scanner = new Scanner(InputScanner.getPath(classname))) {
            return scanner.findAll("\\d+,\\d+ -> \\d+,\\d+").map(value -> {
                String s = value.group().replaceAll(",|( -> )", " ");
                String[] vals = s.split(" ", 4);
                return new Vent(new Point(Integer.parseInt(vals[0]), Integer.parseInt(vals[1])),
                        new Point(Integer.parseInt(vals[2]), Integer.parseInt(vals[3])));
            }).toList();
        }
    }
}
