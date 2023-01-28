package pasquel.wladimir.store;

public class Inventory {
    public static void produceInventory(Item[] items) {
        int totalCount = 0;
        double totalValvue = 0.0;
        
        System.out.printf("%-30s%10s%10s\n", "Title","Price","Quantity");
        for (Item item : items) {
            if(item !=null){
                System.out.printf("%-30s%10.2f%7d\n", item.getTitle(),item.getPrice(),item.getQuantity());
                totalCount+=item.getQuantity();
                totalValvue+=item.getQuantity()*item.getPrice();
            }
        }
        System.out.printf("%-30s%10.2f%7d\n", "Total", totalValvue,totalCount);
    }
}
