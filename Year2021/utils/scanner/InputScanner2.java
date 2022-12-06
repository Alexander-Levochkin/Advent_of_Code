package Year2021.utils.scanner;

import java.io.IOException;

public interface InputScanner2<T, B> extends InputScanner<T> {
    B scan2(String classname) throws IOException;
}
