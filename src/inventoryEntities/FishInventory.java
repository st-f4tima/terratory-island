package inventoryEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import base.Inventory;
import base.Player;
import itemEntities.fish.Fish;
import utils.InputUtils;
import java.util.Scanner;

public class FishInventory extends Inventory{
    private Map<String, List<Fish>> caughtFishes;

    public FishInventory() {
        super();
        this.caughtFishes = new HashMap<>();
    }

    public void addFish(Fish fish){
        String fishName = fish.getName();

        this.caughtFishes.computeIfAbsent(fishName, k -> new ArrayList<>()).add(fish);

      /*   super.addItem(fishName, fish.getcaughtFishWeight()); */

        System.out.println(fishName + " (" + fish.getcaughtFishWeight() + " kg) added to inventory.");
    }

    //need lagyan kasi dynamic at random ang weight ng fish
    public int countAllFishes(){
        int count = 0;
        for (List<Fish> list : caughtFishes.values()){
            count += list.size();
        }
        return count;
    }

    public void SellOneFish(Player player, Scanner scanner){
        int fishCounter = 1;

        if (caughtFishes.isEmpty()){
            System.out.println("\nYou have no fish to sell.");
            return;
        }

        System.out.println("\nSelect the number of the fish you wish to sell: ");
        System.out.print("-> ");
        int selectedFish = InputUtils.getValidIntInput(scanner, 1, countAllFishes());

        for (String fishName: new ArrayList<>(this.caughtFishes.keySet())){
            List<Fish> fishList = this.caughtFishes.get(fishName);

            for (int i = 0; i < fishList.size(); i++){
                if (selectedFish == fishCounter) {

                    Fish soldFish = fishList.remove(i);
                    int coins = soldFish.sell();

                    if (coins > 0) {
                        player.gainCoins(coins);
                    }
                    if (fishList.isEmpty()) {
                        caughtFishes.remove(fishName);
                    }
                    player.gainXP(3);
                }
                fishCounter++;
            }
        }
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
    String header = "│ No. │ Fish Name      │ Weight   │ Sell Price   │";
    String separator = "├─────┼────────────────┼──────────┼──────────────┤";
    String bottomLine = "└─────┴────────────────┴──────────┴──────────────┘";

    System.out.println(topLine);
    System.out.println(header);
    System.out.println(separator);

    int count = 1;

    for (Map.Entry<String, List<Fish>> entry : caughtFishes.entrySet()) {
        for (Fish f: entry.getValue()){
            int weight = f.getcaughtFishWeight();
            int sellPrice = f.getCost() * weight;

            String row = String.format(
                "| %-3d | %-14s | %-8d | %-12d |",
                count++,
                f.getName(),
                weight,
                sellPrice
            );

            System.out.println(row);
        }
        }
        System.out.println(bottomLine);
    }
    }

