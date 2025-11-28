package inventoryEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import base.Inventory;
import base.Player;
import itemEntities.fish.Fish;

public class FishInventory extends Inventory{
    private Map<String, List<Fish>> caughtFishes;

    public FishInventory() {
        super();
        this.caughtFishes = new HashMap<>();
    }

    public void addFish(Fish fish){
        String fishName = fish.getName();

        this.caughtFishes.computeIfAbsent(fishName, k -> new ArrayList<>()).add(fish);

        super.addItem(fishName, fish.getYieldAmount());

        System.out.println(fishName + " (" + fish.getYieldAmount() + ") added to inventory.");
    }
    
    public int sellAllFishes(Player player) {
        int totalEarned = 0;
        int totalSold = 0;

        if (caughtFishes.isEmpty()) {
            System.out.println("You haven't caught any fish yet.");
            return 0;
        }

        Iterator<Map.Entry<String, List<Fish>>> mapIterator = caughtFishes.entrySet().iterator();

        while (mapIterator.hasNext()) {
            Map.Entry<String, List<Fish>> entry = mapIterator.next();
            List<Fish> fish = entry.getValue();

            Iterator<Fish> listIterator = fish.iterator();

            while (listIterator.hasNext()) {
                Fish ffish = listIterator.next();
                int coins = ffish.sell();

                if (coins > 0) {
                    totalEarned += coins;
                    totalSold ++;
                    player.gainCoins(coins);
                    listIterator.remove();
                }
            }

            if (fish.isEmpty()){
                mapIterator.remove();
            }
        }

        player.gainXP(totalSold * 5);
        return totalEarned;
    }

    @Override
    public void viewData() {
    System.out.println("\n─────────────── INVENTORY : FISHES ───────────────\n");
    System.out.println("CAUGHT FISHES:");
    if (this.caughtFishes.isEmpty()) {
        System.out.print("You haven't caught any fish yet.\n");
        return;
    }

    String topLine = "┌─────┬────────────────┬──────────┬──────────────┐";
    String header = "│ No. │ Fish Name      │ Quantity │ Sell Price   │";
    String separator = "├─────┼────────────────┼──────────┼──────────────┤";
    String bottomLine = "└─────┴────────────────┴──────────┴──────────────┘";

    System.out.println(topLine);
    System.out.println(header);
    System.out.println(separator);

    int count = 1;

    for (Map.Entry<String, List<Fish>> entry : this.caughtFishes.entrySet()) {
        List<Fish> fish = entry.getValue();

        for (Fish ffish : fish) {
            String row = String.format(
            "│ %-3d │ %-14s │ %-8d │ %-12.2f │",
            count++,
            ffish.getName(),
            ffish.getYieldAmount(),
            ffish.getCost() * 1.5
        );
        System.out.println(row);
        }
    }
    System.out.println(bottomLine);
    }
}
