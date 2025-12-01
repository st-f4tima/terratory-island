package inventoryEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  // add crop to the inventory
  public void addCrop(Crop crop) {
    String cropName = crop.getName();

    this.storedCrops.computeIfAbsent(cropName, k -> new ArrayList<>()).add(crop);
    
    super.addItem(cropName, crop.getYieldAmount());

    System.out.println(cropName + " (" + crop.getYieldAmount() + ") added to inventory.");
  }

  // Sell a specific crop from inventory
  public void SellOneCrop(Player player, Scanner scanner) {
    if(storedCrops.isEmpty()) {
      System.out.println("\nYou have no crop to sell.");
      return;
    }

    int totalCropsCount = 0;
    for(List<Crop> cropList : this.storedCrops.values()) {
      totalCropsCount += cropList.size();
    }

    System.out.println("\nWhich crop would you like to sell? (Enter Number)");
    System.out.println("[0] Cancel Sale");
    System.out.print("-> ");
    int selectedCrop = InputUtils.getValidIntInput(scanner, 0, totalCropsCount);

    if(selectedCrop == 0) {
      System.out.println("Sale cancelled.");
      return;
    }

    int cropCounter = 0;
    boolean cropSold = false;

    for(String cropName : new ArrayList<>(this.storedCrops.keySet())) {
      List<Crop> cropList = this.storedCrops.get(cropName);

      for(int i = 0; i < cropList.size(); i++) {
        cropCounter++;

        if(selectedCrop == cropCounter) {
          Crop soldCrop = cropList.remove(i);
          i--;
          int coins = soldCrop.sell();

          if(coins > 0) {
            player.gainCoins(coins);
          }

          if(cropList.isEmpty()) {
            storedCrops.remove(cropName);
          }
          cropSold = true;
          break;
        }
        if(cropSold) {
          break;
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

    String topLine    = "┌─────┬────────────────┬──────────┬──────────────┐";
    String header     = "│ No. │ Crop Name      │ Quantity │ Sell Price   │";
    String separator  = "├─────┼────────────────┼──────────┼──────────────┤";
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
