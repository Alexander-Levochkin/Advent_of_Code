package Year2022.scanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task3Scanner implements InputScanner<List<String[]>> {

    @Override
    public List<String[]> scan(String classname) throws IOException {
        List<String[]> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(InputScanner.getPathString(classname)))) {
            while (reader.ready()) {
                String[] group = new String[3];
                for (int i = 0; i < 3; i++) group[i] = reader.readLine();
                result.add(group);
            }
        }
        return result;
    }
}
