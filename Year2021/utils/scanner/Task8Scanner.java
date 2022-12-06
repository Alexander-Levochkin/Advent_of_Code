package Year2021.utils.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Task8Scanner implements InputScanner2<List<String[]>, List<String[]>> {
    //seven-segment displays
    @Override
    public List<String[]> scan(String classname) throws IOException {
        return doScan(classname, 0);
    }

    @Override
    public List<String[]> scan2(String classname) throws IOException {
        return doScan(classname, 1);
    }

    private List<String[]> doScan(String classname, int partIndex) throws IOException {
        List<String[]> result = new ArrayList<>();
        for (String line : Files.readAllLines(InputScanner.getPath(classname))) {
            String part = line.split(" \\| ", 2)[partIndex];
            String[] s1 = part.split(" ", 10);
            result.add(s1);
        }
        return result;
    }
}
