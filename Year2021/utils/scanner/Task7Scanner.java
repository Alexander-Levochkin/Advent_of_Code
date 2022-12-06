package Year2021.utils.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Task7Scanner implements InputScanner<List<Integer>> {
    //Crabs
    @Override
    public List<Integer> scan(String classname) throws IOException {
        List<Integer> result = new ArrayList<>();
        for (String line : Files.readAllLines(InputScanner.getPath(classname))) {
            for (String s : line.split(",")) {
                result.add(Integer.parseInt(s));
            }
        }
        return result;
    }
}
