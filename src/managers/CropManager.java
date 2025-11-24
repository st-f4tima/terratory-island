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

  public void displayCropSummary() {
    if (plantedCrops.isEmpty()) {
      System.out.println("\nNo crops planted yet.");
      return;
    }

    String line = "┌─────┬──────────┬─────────┬──────────┬────────────┬─────────────┬─────────────┐";
    String middleLine = "├─────┼──────────┼─────────┼──────────┼────────────┼─────────────┼─────────────┤";
    String bottomLine = "└─────┴──────────┴─────────┴──────────┴────────────┴─────────────┴─────────────┘";

    System.out.println("\nCrop Summary:");
    System.out.println(line);
    System.out.printf("│%-5s│%-10s│%-9s│%-10s│%-12s│%-13s│%-13s│\n",
            "Tile", "Crop", "Days", "Watered", "Fertilized", "Harvestable", "Est. Yield");
    System.out.println(middleLine);

    int tileNumber = 1;
    for (Crop crop : plantedCrops) {
      String watered = crop.isWatered() ? "Yes" : "No";
      String fertilized = crop.isFertilized() ? "Yes" : "No";
      String harvestable = crop.isHarvestable() ? "Yes" : "No";
      String days = crop.getDaysGrown() + "/" + crop.getGrowthDays();

      System.out.printf("│%-5s│%-10s│%-9s│%-10s│%-12s│%-13s│%-13d│\n",
              "T" + tileNumber,
              crop.getName(),
              days,
              watered,
              fertilized,
              harvestable,
              crop.getYieldAmount());
      tileNumber++;
    }
    System.out.println(bottomLine);
  }

  public void plantSeeds(String currentSeason, Player player, Scanner scanner) {
    System.out.println("\n────────────── PLANT NEW SEEDS ──────────────\n");
    System.out.println("Season: " + currentSeason);

    List<Crop> availableCrops = cropCollection.get(currentSeason);

    if(availableCrops == null) {
      System.out.println("No crops can be planted in " + currentSeason + ".");
      waitEnter(scanner);
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
      waitEnter(scanner);
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
        waitEnter(scanner);
        return; 
      } 
      else {
        System.out.println("[Error] Please enter 'y' or 'n'.");
      }
    }
  }

  public void waterCrops(Scanner scanner) {
    System.out.println("\n──────────────── WATER CROPS ────────────────\n");

    if (plantedCrops.isEmpty()) {
      System.out.println("\nNo crops planted.");
      waitEnter(scanner);
      return; 
    }

    while (true) {
      System.out.print("Water all crops? (y/n): ");
      String choice = scanner.next().trim().toLowerCase();

      if (choice.equals("y")) {
        for (Crop crop : plantedCrops) {
          crop.water();
        }
        System.out.println("\n[Success] All crops have been watered!");
        break; 
      } else if (choice.equals("n")) {
        System.out.println("\nNo crops were watered.");
        break; 
      } else {
        System.out.println("[Error] Please enter 'y' or 'n'."); 
      }
    }

    waitEnter(scanner);
    return; 
  }

  public void fertilizeCrops(Scanner scanner) {
    System.out.println("\n────────────── FERTILIZE CROPS ──────────────\n");
    
    if (plantedCrops.isEmpty()) {
      System.out.println("\nNo crops planted.");
      waitEnter(scanner); 
      return;
    }

    while (true) {
      System.out.print("Apply fertilizer to all crops? (y/n): ");
      String choice = scanner.next().trim().toLowerCase();

      if (choice.equals("y")) {
        for (Crop crop : plantedCrops) {
          crop.applyFertilizer();
        }
        System.out.println("\n[Success] All crops have been fertilized!");
        break; 
      } else if (choice.equals("n")) {
        System.out.println("\nNo crops were fertilized.");
        break; 
      } else {
        System.out.println("[Error] Please enter 'y' or 'n'."); 
      }
  }

    waitEnter(scanner); 
    return;
  }

  public void growCrops() {
    
    for(Crop crop : plantedCrops) {
      crop.grow();
    }
  }

  public void harvestCrops(Scanner scanner) {
    boolean anyHarvested = false;
    System.out.println("\n─────────────── HARVEST CROPS ───────────────\n");

    if (plantedCrops.isEmpty()) {
      System.out.println("No crops planted.");
      waitEnter(scanner); 
      return;
    }

    while (true) {
        System.out.print("Harvest ready crops? (y/n): ");
        String choice = scanner.next().trim().toLowerCase();

        if (choice.equals("y")) {
          for (int i = 0; i < plantedCrops.size(); i++) {
            Crop crop = plantedCrops.get(i);
            if (crop.isHarvestable()) {
              crop.harvest();
              cropInventory.addItem(crop.getName(), crop.getYieldAmount());
              plantedCrops.remove(i);
              i--; 
              anyHarvested = true;
            }
          }
          break; 
        } else if (choice.equals("n")) {
          System.out.println("\n\nNo crops were harvested.");
          break; 
        } else {
          System.out.println("[Error] Please enter 'y' or 'n'."); 
        }
    }

    if (!anyHarvested) {
        System.out.println("No crops are ready to harvest.");
    }

    waitEnter(scanner); 
    return;
}

  // helper
  private void waitEnter(Scanner scanner) {
    System.out.println("\nPress ENTER to continue...");
    scanner.nextLine();
    scanner.nextLine();
    }

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

