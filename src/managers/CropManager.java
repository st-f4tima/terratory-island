package managers;

import java.util.ArrayList;
import java.util.List;
import base.Player;
import java.util.Scanner;
import itemEntities.crop.Crop;
import itemEntities.crop.spring.*;
import itemEntities.crop.summer.*;
import itemEntities.crop.fall.*;

public class CropManager {
  List<Crop> springCrops;
  List<Crop> summerCrops;
  List<Crop> fallCrops;
  List<Crop> plantedCrops;

  public CropManager() {
    springCrops = List.of(new Potato(), new Strawberry(), new Cauliflower());
    summerCrops = List.of(new Wheat(), new Melon(), new Starfruit());
    fallCrops = List.of(new Grape(), new Pumpkin(), new SweetGemBerry());
    plantedCrops = new ArrayList<>();
  }

  public void plantSeeds(String currentSeason, Player player, Scanner scanner) {
    System.out.println("\n────────────── PLANT NEW SEEDS ──────────────\n");
    System.out.println("Season: " + currentSeason);

    List<Crop> availableCrops;

    switch (currentSeason) {
      case "Spring":
        availableCrops = springCrops;
        break;
      case "Summer": 
        availableCrops = summerCrops;
        break;
      case "Fall":
        availableCrops = fallCrops;
        break;
      case "Winter":
        System.out.println("No crops can be planted in Winter.");
        return;
      default:
        System.out.println("Invalid season.");
        return;
    }

    for(int i = 0; i < availableCrops.size(); i++) {
      Crop crop = availableCrops.get(i);
      int requiredLevel = crop.getLevelRequired();
      System.out.printf("\n%d. %s (Level required: %d)", (i + 1), crop.getName(), requiredLevel);
    }

    while (true) {
      System.out.print("\n-> ");
      int seedChoice = scanner.nextInt();

      if (seedChoice < 1 || seedChoice > availableCrops.size()) {
        System.out.println("Error: Please select a number from 1 to " + availableCrops.size() + ".");
        continue;
      }  

      Crop chosenSeed = availableCrops.get(seedChoice - 1);

      if (player.getLevel() < chosenSeed.getLevelRequired()) {
        System.out.println("You must be level " + chosenSeed.getLevelRequired() + " to plant " + chosenSeed.getName() + ".");
        continue;
      }

      System.out.print("How many would you like to plant? -> ");
      int quantity = scanner.nextInt();

      if (quantity < 1) {
        System.out.println("Please enter a valid quantity.");
        continue;
      }

      for (int i = 0; i < quantity; i++) {
        Crop newCrop = chosenSeed.createCopy();
        plantedCrops.add(newCrop);
      }
      
      System.out.println("Successfully planted " + quantity + " " + chosenSeed.getName() + " crop(s)!");
      break;
    }
  }
}