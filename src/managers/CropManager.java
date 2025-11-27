package managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import base.Player;
import itemEntities.crop.Crop;
import utils.InputUtils;

import itemEntities.crop.spring.*;
import itemEntities.crop.summer.*;
import itemEntities.crop.fall.*;

public class CropManager {
  private Player player;
  private Map<String, List<Crop>> cropCollection;
  private List<Crop> plantedCrops;

  public CropManager(Player player) {
    this.player = player;
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
      System.out.println("\nCROP SUMMARY:");
      System.out.print("No crops planted yet.\n");
      return;
    }

    String topLine = "┌─────┬──────────┬─────────┬──────────┬────────────┬─────────────┬─────────────┐";
    String seperator = "├─────┼──────────┼─────────┼──────────┼────────────┼─────────────┼─────────────┤";
    String bottomLine = "└─────┴──────────┴─────────┴──────────┴────────────┴─────────────┴─────────────┘";

    System.out.println("\nCROP SUMMARY:");
    System.out.println(topLine);
    System.out.printf("│%-5s│%-10s│%-9s│%-10s│%-12s│%-13s│%-13s│\n",
            "Tile", "Crop", "Days", "Watered", "Fertilized", "Harvestable", "Est. Yield");
    System.out.println(seperator);

    int tileNumber = 1;
    for (Crop crop : plantedCrops) {
      String watered = crop.isWatered() ? "Hydrated" : "Dry";
      String fertilized = crop.isFertilized() ? "Enriched" : "Basic";
      String harvestable = crop.isHarvestable() ? "Mature" : "Growing";
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
      InputUtils.waitEnter(scanner);
      return;
    }

    // display available crops for the current season
    System.out.println("\nAvailable Seeds:");
    for(int i = 0; i < availableCrops.size(); i++) {
      Crop crop = availableCrops.get(i);
      System.out.printf("[%d] %-15s (Lvl Req: %d)\n", (i + 1), crop.getName(), crop.getLevelRequired());
    }

    System.out.println("\nChoose a seed to plant:");
    System.out.print("-> ");
    int seedChoice = InputUtils.getValidIntInput(scanner, 1, availableCrops.size());

    Crop chosenSeed = availableCrops.get(seedChoice - 1);

    if (player.getLevel() < chosenSeed.getLevelRequired()) {
      System.out.println("\n[!] You must be level " + chosenSeed.getLevelRequired() + " to plant " + chosenSeed.getName() + ".");
      InputUtils.waitEnter(scanner);
      return;
    }

    System.out.println("\nSpecify the number of seeds to plant:");
    System.out.print("-> ");
    int quantity = InputUtils.getValidIntInput(scanner, 1, 100); 

    for (int i = 0; i < quantity; i++) {
      this.plantedCrops.add(chosenSeed.createCopy());
    }

    System.out.println("\n[Success] You planted " + quantity + " " + chosenSeed.getName() + "(s)!");
    player.gainXP(quantity * 2); // player gains xp!

    while (true) {
      System.out.print("\nPlant again? (y/n): ");
      String plantAgain = scanner.next().trim().toLowerCase();

      if (plantAgain.equals("y")) {
        scanner.nextLine();
        plantSeeds(currentSeason, player, scanner);
        return;
      } 
      else if (plantAgain.equals("n")) {
        InputUtils.waitEnter(scanner);
        scanner.nextLine();
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
      InputUtils.waitEnter(scanner);
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
        player.gainXP(plantedCrops.size() * 2); // player gains xp!
        break; 
      } else if (choice.equals("n")) {
        System.out.println("\nNo crops were watered.");
        break; 
      } else {
        System.out.println("[Error] Please enter 'y' or 'n'."); 
      }
    }

    InputUtils.waitEnter(scanner);
    scanner.nextLine();
    return; 
  }

  public void fertilizeCrops(Scanner scanner) {
    System.out.println("\n────────────── FERTILIZE CROPS ──────────────\n");
    
    if (plantedCrops.isEmpty()) {
      System.out.println("\nNo crops planted.");
      InputUtils.waitEnter(scanner);
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
        player.gainXP(plantedCrops.size() * 2); // player gains xp!
        break; 
      } else if (choice.equals("n")) {
        System.out.println("\nNo crops were fertilized.");
        break; 
      } else {
        System.out.println("[Error] Please enter 'y' or 'n'."); 
      }
  }

    InputUtils.waitEnter(scanner);
    scanner.nextLine();
    return;
  }

  public void growCrops() {
    if(plantedCrops.isEmpty()) return;

    int grewCount = 0;
    int notGrewCount = 0;

    for(Crop crop : plantedCrops) {
      if(crop.isWatered() && crop.isFertilized()) {
        crop.grow();
        grewCount++;
      } else {
        notGrewCount--;
      }
      crop.resetDailyCare();
    }

    if (grewCount == plantedCrops.size()) {
      System.out.println("\nAll your crops grew today!");
    } else if (grewCount > 0) {
      System.out.println("\n" + grewCount + " crop(s) grew today, " + notGrewCount + " did not due to missing care.");
    } else {
      System.out.println("\nNone of your crops grew today. Make sure to water and fertilize them!");
    }
  }
  
  public void harvestCrops(Scanner scanner) {
    boolean anyHarvested = false;
    int harvestedCount = 0;
    System.out.println("\n─────────────── HARVEST CROPS ───────────────\n");

    if (plantedCrops.isEmpty()) {
      System.out.println("No crops planted.");
      InputUtils.waitEnter(scanner);
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
              player.getCropInventory().addCrop(crop);
              plantedCrops.remove(i);
              i--; 
              anyHarvested = true;
              harvestedCount++;
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

    if(harvestedCount > 0) {
      player.gainXP(plantedCrops.size() * 10); // player gains xp!
    } else {
      System.out.println("No crops are ready to harvest.");
    }
    
    InputUtils.waitEnter(scanner);
    scanner.nextLine();
    return;
  }
}

