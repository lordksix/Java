package pasquel.wladimir.store;
import static  java.lang.System.out;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BookExerciser {
    public static void main(String[] args) {
        Path cwd = Paths.get(System.getProperty("user.dir"));
        String location = cwd.resolve("books.txt").toString();
        System.out.println(location);
        List<Book> books = readBooksFromFile(location);
        for (Book book: books){
            out.println(book.getTitle());
        }

    }

    public static ArrayList readBooksFromFile(String name) {
        ArrayList books = new ArrayList();
        FileInputStream inFile = null;
        InputStreamReader inReader = null;
        LineNumberReader lineReader = null;
        try {
            inFile = new FileInputStream(name);
            inReader = new InputStreamReader(inFile);
            lineReader = new LineNumberReader(inReader);
            String title = lineReader.readLine();
            while (title != null) {
                String author = lineReader.readLine();
                String sPrice = lineReader.readLine();
                double price = Double.parseDouble(sPrice);
                Book book = new Book(title, price, 5, author, null, "NON-FICTION");
                books.add(book);
                title = lineReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (lineReader != null) {
                    lineReader.close();}
                if (inFile != null) {
                    inFile.close();}
                if (inReader!=null) {
                    inReader.close();}
            } catch (IOException e) {
                e.printStackTrace();}
        }


        return books;
    }
}
