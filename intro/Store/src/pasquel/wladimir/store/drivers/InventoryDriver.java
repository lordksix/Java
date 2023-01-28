package pasquel.wladimir.store.drivers;
import java.util.Date;

import pasquel.wladimir.store.Artist;
import pasquel.wladimir.store.Book;
import pasquel.wladimir.store.CD;
import pasquel.wladimir.store.ClassicalCD;
import pasquel.wladimir.store.Inventory;
import pasquel.wladimir.store.Item;

public class InventoryDriver {
    public static void main(String[] args) {
        Item[] myInventory = new Item[50];
        myInventory[0]= new Book("Godzilla on Holiday", 24.95, 5, "Wesley Wynham-Price", "Ransom House", "Fiction");
        myInventory[1]= new Book("Luch Ness Memories", 49.95, 1, "Fred MacMurray", "Penguin Press", "Fiction");
        myInventory[2]= new Book("MVS JCL", 89.95, 3, "Steve Balmer", "Microsoft Press", "Non-Fiction");
        myInventory[3]= new Book("Lingo in a Nutshell", 19.95, 8, "Bill Bates", "O'Reilly", "Non-Fiction");
        myInventory[4]= new Book("Grid Computing", 79.95, 2, "Bobby Beowold", "Trouser Press", "Non-Fiction");

        Artist artist1 = new Artist("YES");
        myInventory[5] = new CD("Going for the One", 12.95, 4, artist1, new Date("07/07/1977"));

        Artist artist2 = new Artist("Genesis");
        myInventory[6] = new CD("Trick of the Tail", 12.95, 7, artist2, new Date("07/07/1977"));

        myInventory[7] = new CD("Going Down The Country", 12.95, 10, new Artist("Bozos"), new Date("09/07/1987"));

        String[] performers1 = {"Henry", "Elizabeth", "Franz", "Arthur"};
        myInventory[8] = new ClassicalCD("Romeo and Juliet", 22.95, 1, "Joe Green", performers1, "New York", new Date("01/01/2001"));

        String[] performers2 = {"Vivaldi", "Johnson","Arturo","Wenselous"};
        myInventory[9] = new ClassicalCD("A Romp in the Park", 22.95, 3, "Beethovem", performers2, "Hamburg", new Date("01/01/2001"));

        Inventory.produceInventory(myInventory);
    }
    
}
