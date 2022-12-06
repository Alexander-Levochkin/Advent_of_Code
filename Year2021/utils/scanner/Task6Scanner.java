package Year2021.utils.scanner;

import java.io.IOException;
import java.nio.file.Files;

public class Task6Scanner implements InputScanner<long[]> {
    //Lanternfish
    @Override
    public long[] scan(String classname) throws IOException {
        long[] result = new long[10];
        for (String line : Files.readAllLines(InputScanner.getPath(classname))) {
            for (String s : line.split(",")) {
                //increment the amount of times the same Lanternfish appears
                result[Integer.parseInt(s)]++;
            }
        }
        return result;
    }
}
