package Year2021.utils.scanner;

import java.io.IOException;

public class TaskScanner {
    public static <T> T scan(InputScanner<T> scanner, String classpath) throws IOException {
        return scanner.scan(classpath);
    }

    public static <T, B> B scan2(InputScanner2<T, B> scanner, String classpath) throws IOException {
        return scanner.scan2(classpath);
    }
}
