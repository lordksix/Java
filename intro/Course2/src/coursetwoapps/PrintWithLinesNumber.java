package coursetwoapps;
import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;

public class PrintWithLinesNumber {
    public static void main(String[] args) {
        System.out.printf("Current working directory: %s%n", System.getProperty("user.dir"));

        try (InputStream infile = new FileInputStream("src/transport/Car.java");
        Reader inStreamReader = new InputStreamReader(infile);
        LineNumberReader lineReader = new  LineNumberReader(inStreamReader)) {
            lineReader.lines().forEach(line->out.printf("%d: %s%n", lineReader.getLineNumber(),line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
