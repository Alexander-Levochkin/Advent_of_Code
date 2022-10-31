package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ToListScanner {

    public static List<String> toString(String path) throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(path))) {
            return stream.toList();
        }
    }

    public static List<Integer> toInteger(String path) throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(path))) {
            return stream.mapToInt(Integer::parseInt).boxed().toList();
        }
    }

    public static List<Long> toLong(String path) throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(path))) {
            return stream.mapToLong(Long::parseLong).boxed().toList();
        }
    }

    public static List<Double> toDouble(String path) throws IOException {
        try (Stream<String> stream = Files.lines(Path.of(path))) {
            return stream.mapToDouble(Double::parseDouble).boxed().toList();
        }
    }
}
