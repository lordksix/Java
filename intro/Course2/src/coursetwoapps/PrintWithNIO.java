package coursetwoapps;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrintWithNIO {
    public static void main(String[] args) {
        System.out.printf("Current working directory: %s%n", System.getProperty("user.dir"));
        Path file = Paths.get("src/transport/Car.java");
        try (InputStream infile = Files.newInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(infile))) {
            reader.lines().forEach(line->out.printf("%s%n",line));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
