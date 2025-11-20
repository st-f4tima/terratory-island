package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import base.Player;
import itemEntities.crop.Crop;
import inventoryEntities.CropInventory;

import itemEntities.crop.spring.*;
import itemEntities.crop.summer.*;
import itemEntities.crop.fall.*;

public class CropManager {
  private CropInventory cropInventory;
  private Map<String, List<Crop>> cropCollection;
  private List<Crop> plantedCrops;

  public CropManager() {
    this.cropInventory = new CropInventory();
    this.cropCollection = new HashMap<>();
    this.plantedCrops = new ArrayList<>();
    initializeCrops();
  }

  private void initializeCrops() {
    cropCollection.put("Spring", List.of(new Potato(), new Strawberry(), new Cauliflower()));
    cropCollection.put("Summer", List.of(new Wheat(), new Melon(), new Starfruit()));
    cropCollection.put("Fall", List.of(new Grape(), new Pumpkin(), new SweetGemBerry()));
  }

  public void plantSeeds(String currentSeason, Player player, Scanner scanner) {
    System.out.println("\n────────────── PLANT NEW SEEDS ──────────────\n");
    System.out.println("Season: " + currentSeason);

    List<Crop> availableCrops = cropCollection.get(currentSeason);

    if(availableCrops == null) {
      System.out.println("No crops can be planted in " + currentSeason + ".");
      return;
    }

    // display available crops for the current season
    System.out.println("\nAvailable Seeds:");
    for(int i = 0; i < availableCrops.size(); i++) {
      Crop crop = availableCrops.get(i);
      System.out.printf("%d. %-15s (Lvl Req: %d)\n", (i + 1), crop.getName(), crop.getLevelRequired());
    }

    System.out.print("\nChoose a seed to plant:");
    int seedChoice = getValidIntInput(scanner, availableCrops.size());
    Crop chosenSeed = availableCrops.get(seedChoice - 1);

    if (player.getLevel() < chosenSeed.getLevelRequired()) {
      System.out.println("\n[!] You must be level " + chosenSeed.getLevelRequired() + " to plant " + chosenSeed.getName() + ".");
      return;
    }

    System.out.print("\nSpecify the number of seeds to plant:");
    int quantity = getValidIntInput(scanner, 99); 

    for (int i = 0; i < quantity; i++) {
      this.plantedCrops.add(chosenSeed.createCopy());
    }
    System.out.println("\n[Success] You planted " + quantity + " " + chosenSeed.getName() + "(s)!");

      while (true) {
      System.out.print("\nPlant again? (y/n): ");
      String plantAgain = scanner.next().trim().toLowerCase();

      if (plantAgain.equals("y")) {
        scanner.nextLine();
        plantSeeds(currentSeason, player, scanner);
        return;
      } 
      else if (plantAgain.equals("n")) {
          System.out.println("\nPress ENTER to go back...");
          scanner.nextLine(); 
          scanner.nextLine(); 
          return; 
      } 
      else {
          System.out.println("[Error] Please enter 'y' or 'n'.");
      }
    }
  }

  public void waterCrops(Scanner scanner) {
    if(this.plantedCrops.isEmpty()) {
      System.out.println("\nYou have no crops planted.");
      return;
    }

    System.out.println("\n──────────────── WATER CROPS ────────────────\n");

    Map<String, List<Crop>> groupedCrops = new HashMap<>();
    for(Crop crop : plantedCrops) {
      String cropName = crop.getName();
      if(!groupedCrops.containsKey(cropName)) {
        groupedCrops.put(cropName, new ArrayList<>());
      }

      groupedCrops.get(cropName).add(crop);
    }

    int index = 1;
    for(String cropName : groupedCrops.keySet()) {
      List<Crop> group = groupedCrops.get(cropName);
      int cropCount = group.size();

      int totalYield = cropCount * group.get(0).getYieldAmount();
      
      boolean allWatered = true;
      for(Crop crop : group) {
        if(!crop.isWatered()) {
          allWatered = false;
          break;
        }
      }
  
      String cropStatus = allWatered ? "[Watered]" : "[Dry]";
      
      System.out.printf("%d. %-12s %s\n", index++, "Crop:", cropName);
      System.out.printf("   %-12s %dx\n", "Quantity:", cropCount);
      System.out.printf("   %-12s %s\n", "Status:", cropStatus);
      System.out.printf("   %-12s %d\n", "Est Yield:", totalYield);
    }

    System.out.println("\n\n\"So, farmer genius, what's the move?\"");
    System.out.println("1. Water all crops");
    System.out.println("2. Do something else");
    System.out.print("->");


    int choice = getValidIntInput(scanner, 2);
    if(choice == 1) {
      for(Crop crop : plantedCrops) {
        crop.water();
      }
      System.out.println("\nYou watered all your crops!");
      System.out.println("\nPress ENTER to continue...");
      scanner.nextLine(); 
    }
  }

  // helper
  private int getValidIntInput(Scanner scanner, int max) {
    int choice;
    while (true) {
      System.out.print(" ");
      if(!scanner.hasNextInt()) {
        System.out.println("[Error] Please enter a number.");
        scanner.nextLine(); 
        continue;
      }

      choice = scanner.nextInt();
      scanner.nextLine();

      if(choice < 1 || choice > max) {
        System.out.println("[Error] Choose 1-" + max + ".");
        continue;
      }
      return choice;
    }
  }
}

