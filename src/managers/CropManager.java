package managers;

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

  public CropManager() {
    this.cropInventory = new CropInventory();
    this.cropCollection = new HashMap<>();
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

    System.out.println("\nChoose a seed to plant:");
    int seedChoice = getValidIntInput(scanner, availableCrops.size());
    Crop chosenSeed = availableCrops.get(seedChoice - 1);

    if (player.getLevel() < chosenSeed.getLevelRequired()) {
      System.out.println("\n[!] You must be level " + chosenSeed.getLevelRequired() + " to plant " + chosenSeed.getName() + ".");
      return;
    }

    System.out.println("\nSpecify the number of seeds to plant:");

    int quantity = getValidIntInput(scanner, 99); 
    for (int i = 0; i < quantity; i++) {
      cropInventory.plantCrops(chosenSeed.createCopy());
    }
    System.out.println("\n[Success] You planted " + quantity + " " + chosenSeed.getName() + "(s)!");

  }


  public void waterCrops(Scanner scanner) {
    if(!cropInventory.hasPlantedCrops()) {
      System.out.println("\nYou have no crops planted.");
      return;
    }

    System.out.println("\n──────────────── WATER CROPS ────────────────\n");

    List <Crop> plantedCrops = cropInventory.getPlantedCrops();

    for(int i = 0; i < plantedCrops.size(); i++) {
      Crop crop = plantedCrops.get(i);
      String cropStatus;
      if(crop.isWatered()) {
        cropStatus = "[Watered]";
      } else {
        cropStatus = "[Dry]";
      }

      System.out.printf("%d. %-12s %s\n", (i + 1), crop.getName(), cropStatus);
    }

    System.out.println("\n\"So, farmer genius, what's the move?\"");
    System.out.println("1. Water all crops");
    System.out.println("2. Do something else\n");


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
      System.out.print("-> ");
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

