package Year2021.utils.scanner;

import helper.Constants;

import java.io.IOException;
import java.nio.file.Path;

public interface InputScanner<T> {
    /**
     * The base method
     * @param classname the name of the class which input file we want to scan (Achieved by calling {@code getClass().getSimpleName()})
     * @return the input in the specific format (which is passed as a class parameter)
     * @throws IOException if the scan went wrong
     */
    T scan(String classname) throws IOException;

    static Path getPath(String className) {
        return Path.of(getPathString(className));
    }

    static String getPathString(String className) {
        return "Year2021\\inputs\\" + className + ".txt";
    }
}
