package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ToMapScanner {

    public static Map<String, String> toStringString(String path) throws IOException {
        Map<String, String> map = new HashMap<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            lines.forEach(line -> {
                String[] pair = line.split(" ", 2);
                map.put(pair[0], pair[1]);
            });
        }
        return map;
    }

    public static Map<Integer, Integer> toIntegerInteger(String path) throws IOException {
        Map<Integer, Integer> map = new HashMap<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            lines.forEach(line -> {
                String[] pair = line.split(" ", 2);
                map.put(Integer.parseInt(pair[0]), Integer.parseInt(pair[1]));
            });
        }
        return map;
    }

    public static Map<String, Integer> toStringInteger(String path) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            lines.forEach(line -> {
                String[] pair = line.split(" ", 2);
                map.put(pair[0], Integer.parseInt(pair[1]));
            });
        }
        return map;
    }
}
