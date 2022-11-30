package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {

    /**
     * This function return content of given file
     * @param path file to be read
     * @return content of file
     */
    public static String loadResource(String path) throws IOException {
        return Files.readString(Path.of(path));
    }

}
