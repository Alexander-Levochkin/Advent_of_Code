package Year2022.scanner;

import java.io.IOException;

public interface InputScanner2<T, B> extends InputScanner<T> {
    B scan2(String classname) throws IOException;
}
