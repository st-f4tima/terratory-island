package inventoryEntities;

import base.Inventory;
import base.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProduceInventory extends Inventory{
    private Map<String, Integer> produce;

    private static final Map<String, Integer> PRODUCT_PRICES = Map.of(
        "Milk", 150,
        "Egg", 50,
        "Wool", 500,
        "Truffle", 1000
    );
    private static final int XP_PER_ITEM = 5;

    public ProduceInventory() {
        super();
        this.produce = new HashMap<>();
    }

    public int sellAllProduce(Player player) {
        int totalEarned = 0;
        int totalSold = 0;

        if (this.itemCount.isEmpty()) {
            System.out.println("You have no animal produce to sell.");
            return 0;
        }

        Iterator<Map.Entry<String, Integer>> iterator = this.itemCount.entrySet().iterator();

        System.out.println("\n───────────────── SELLING PRODUCE ─────────────────\n");
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String produceName = entry.getKey();
            Integer quantity = entry.getValue();
            
            // get price from constant map, defaulting to 0 if an unknown item somehow exists
            int unitPrice = PRODUCT_PRICES.getOrDefault(produceName, 0);
            int earnedForProduct = unitPrice * quantity;

            totalEarned += earnedForProduct;
            totalSold += quantity;

            System.out.println("Sold " + quantity + " " + produceName + "(s) for " + earnedForProduct + " coins.");
            iterator.remove();
        }

        player.gainCoins(totalEarned);
        player.gainXP(totalSold * XP_PER_ITEM);
        
        System.out.println("\n[Success] Total sold: " + totalSold + ".");
        System.out.println("You earned a total of " + totalEarned + " coins!");

        return totalEarned;
    }

    @Override
    public void viewData() {
        System.out.println("\n───────────── INVENTORY : PRODUCE ─────────────\n");
        if(this.itemCount.isEmpty()){
            System.out.println("You do not have any produce right now.");
            return;
        }

        String topLine    = "┌─────────────┬──────────┐";
        String header     = "│   Produce   │ Quantity │";
        String separator  = "├─────────────┼──────────┤";
        String bottomLine = "└─────────────┴──────────┘";

        System.out.println(topLine);
        System.out.println(header);
        System.out.println(separator);

        for(Map.Entry<String, Integer> entry : this.itemCount.entrySet()){
            String product = entry.getKey();
            Integer amount = entry.getValue();
            String row = String.format("│ %-10s │ %8d │", product, amount);
            System.out.println(row);
        }
        System.out.println(bottomLine);
    }
}
