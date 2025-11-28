package inventoryEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;
import base.Inventory;
import base.Player;
import itemEntities.crop.Crop;
import utils.InputUtils;

public class CropInventory extends Inventory {
  private Map<String, List<Crop>> storedCrops;

  public CropInventory() {
    super();
    this.storedCrops = new HashMap<>();
  }

  public void addCrop(Crop crop) {
    String cropName = crop.getName();

    this.storedCrops.computeIfAbsent(cropName, k -> new ArrayList<>()).add(crop);
    
    super.addItem(cropName, crop.getYieldAmount());

    System.out.println(cropName + " (" + crop.getYieldAmount() + ") added to inventory.");
  }

  // public void sellAllCrops(Player player) {
  //   int totalEarned = 0;
  //   int totalSold = 0;

  //   if (storedCrops.isEmpty()) {
  //       System.out.println("You have no harvested crops.");
  //       return;
  //   }
    
  //   Iterator<Map.Entry<String, List<Crop>>> mapIterator = storedCrops.entrySet().iterator();

  //   while (mapIterator.hasNext()) {
  //     Map.Entry<String, List<Crop>> entry = mapIterator.next();
  //     List<Crop> crops = entry.getValue();

  //     Iterator<Crop> listIterator = crops.iterator();

  //     while (listIterator.hasNext()) {
  //       Crop crop = listIterator.next();
  //       int coins = crop.sell();

  //       if (coins > 0) {
  //         totalEarned += coins;
  //         totalSold++;
  //         player.gainCoins(coins);
  //         listIterator.remove(); 
  //       }
  //     }

  //     if (crops.isEmpty()) {
  //       mapIterator.remove();
  //     }
  //   }
    
  //   player.gainXP(totalSold * 8);
  // }

  public void SellOneCrop(Player player, Scanner scanner) {
    int cropCounter = 1;

    if(storedCrops.isEmpty()) {
      System.out.println("\nYou have no crop to sell.");
      return;
    }

    System.out.println("\nSelect the number of the crop you wish to sell:");
    System.out.print("-> ");
    int selectedCrop = InputUtils.getValidIntInput(scanner, 1, storedCrops.size() + 1);

    for(String cropName : new ArrayList<>(this.storedCrops.keySet())) {
      List<Crop> cropList = this.storedCrops.get(cropName);

      for(int i = 0; i < cropList.size(); i++) {
        if(selectedCrop == cropCounter) {
          Crop soldCrop = cropList.remove(i);
          int coins = soldCrop.sell();

          if(coins > 0) {
            player.gainCoins(coins);
          }

          if(cropList.isEmpty()) {
            storedCrops.remove(cropName);
          }
        }
      }
    }
    player.gainXP(20);
  }


  @Override
  public void viewData() {
    System.out.println("\n─────────────── INVENTORY : CROPS ───────────────\n");
    System.out.println("HARVESTED CROPS:");
    if (this.storedCrops.isEmpty()) {
      System.out.print("You currently have no harvested crops.\n");
      return;
    }

    String topLine = "┌─────┬────────────────┬──────────┬──────────────┐";
    String header = "│ No. │ Crop Name      │ Quantity │ Sell Price   │";
    String separator = "├─────┼────────────────┼──────────┼──────────────┤";
    String bottomLine = "└─────┴────────────────┴──────────┴──────────────┘";

    System.out.println(topLine);
    System.out.println(header);
    System.out.println(separator);

    int count = 1;

    for (Map.Entry<String, List<Crop>> entry : this.storedCrops.entrySet()) {
        List<Crop> crops = entry.getValue();

        for (Crop crop : crops) {
          String row = String.format(
            "│ %-3d │ %-14s │ %-8d │ %-12.2f │",
            count++,
            crop.getName(),
            crop.getYieldAmount(),
            crop.getCost() * 1.5
          );
          System.out.println(row);
        }
    }
    System.out.println(bottomLine);
  }
}
