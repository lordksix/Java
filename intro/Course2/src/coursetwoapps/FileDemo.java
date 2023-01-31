package coursetwoapps;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.nio.file.DirectoryStream;

public class FileDemo {
    public static boolean contains(Path file, String text) {
        try {
            return Files.isRegularFile(file)&&Files.lines(file).anyMatch(line->line.contains(text));
        } catch (IOException e) {
            return false;
        }        
    }
    public static void main(String[] args) throws IOException{
        PathMatcher javaFiles = FileSystems.getDefault().getPathMatcher("glob:**/*.java");
        Path cwd = Paths.get(System.getProperty("user.dir")); //FileSystems.getDefault().getPath(System.getProperty("user.dir"));
        System.out.println(cwd);
        Stream<Path> javaPaths = Files.walk(cwd).filter(path->javaFiles.matches(path));
        javaPaths.filter(path -> FileDemo.contains(path, "System")).forEach(System.out::println);

        System.out.println("---------------------");
        
        cwd = cwd.resolve("src\\CourseTwo");
        try (DirectoryStream<Path> stream =
        Files.newDirectoryStream(cwd, "*.{java,class,jar}")) {
            for (Path entry: stream) {
                System.out.println(entry.getFileName());
        }
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
}
    }
}
