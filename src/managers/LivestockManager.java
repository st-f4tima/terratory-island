package managers;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

import base.Player;
import utils.InputUtils;
import inventoryEntities.ProduceInventory;
import inventoryEntities.LivestockInventory;
import itemEntities.animal.Livestock;
import itemEntities.animal.egg.*;
import itemEntities.animal.milk.*;
import itemEntities.animal.unique.*;

public class LivestockManager {
  private LivestockInventory livestockInventory;
  private ProduceInventory produceInventory;
  private Map<String, Integer> livestockGiftingStatus;
  private List<Livestock> allLivestockSpecies;
  //  track which species have been gifted and how many times
  //  Map<SpeciesName, TimesGifted>


  public LivestockManager(LivestockInventory animalInv, ProduceInventory produceInv) {
      this.livestockInventory = animalInv;
      this.produceInventory = produceInv;
      this.livestockGiftingStatus = new HashMap<>();
      initializeLivestockSpecies();
  }

//  method to populate the list of all species and initialize the gifting status map
  private void initializeLivestockSpecies() {
      // base animals (dummy objects for getting name and levelRequired)
      this.allLivestockSpecies = List.of(
          new Chicken("Chick", 0), 
          new Duck("Duckling", 0),
          new Cow("Calf", 0),
          new Goat("Kid", 0),
          new Sheep("Lamb", 0),
          new Pig("Piglet", 0)
      );
      
      //  initialize gifting status for all species to 0
      for (Livestock species : allLivestockSpecies) {
          this.livestockGiftingStatus.put(species.getName(), 0);
      }
  }

  //  method to check player level and grant livestock up to twice per species
  public void checkAndGrantLivestock(Player player) {
    int playerLevel = player.getLevel();
    boolean giftedAnimal = false;

    System.out.println("\n──────────────── ANIMAL ACQUISITIONS ────────────────");

    for (Livestock species : allLivestockSpecies) {
      String speciesName = species.getName();
      int levelRequired = species.getLevelRequired();
      int timesGifted = livestockGiftingStatus.getOrDefault(speciesName, 0);
      
      //  check if player meets level requirement AND has been gifted less than twice
      if (playerLevel >= levelRequired && timesGifted < 2) {
        
        Livestock newAnimal;
        String petName;
        
        //  find class type and create a new instance
        if (speciesName.equals("Chicken")) {
            petName = "Clucky #" + (timesGifted + 1);
            newAnimal = new Chicken(petName, 0);
        } else if (speciesName.equals("Duck")) {
            petName = "Quacky #" + (timesGifted + 1);
            newAnimal = new Duck(petName, 0);
        } else if (speciesName.equals("Cow")) {
            petName = "Moochelle #" + (timesGifted + 1);
            newAnimal = new Cow(petName, 0);
        } else if (speciesName.equals("Goat")) {
            petName = "Billy #" + (timesGifted + 1);
            newAnimal = new Goat(petName, 0);
        } else if (speciesName.equals("Sheep")) {
            petName = "Barbara #" + (timesGifted + 1);
            newAnimal = new Sheep(petName, 0);
        } else if (speciesName.equals("Pig")) {
            petName = "Oinkers #" + (timesGifted + 1);
            newAnimal = new Pig(petName, 0);
        } else {
            continue;
        }

        //  grant animal
        //  The addAnimal function in LivestockInventory will handle the print statement for adding
        player.getLivestockInventory().addAnimal(newAnimal); 
        System.out.println("🎉 Congrats! You reached Level " + levelRequired + "! You were gifted a new " + speciesName + " named " + petName + ".");
        
        //  update gifting status
        livestockGiftingStatus.put(speciesName, timesGifted + 1);
        giftedAnimal = true;
      }
    }
    if(!giftedAnimal) {
        System.out.println("No new animals were acquired today.");
    }
    System.out.println("───────────────────────────────────────────────────\n");
  }

  //  progress
  public void growAllLivestock() {
    if (livestockInventory.showAllItem().isEmpty()) {
      return;
    }
    
    int grewCount = 0;
    Map<String, List<Livestock>> petMap = livestockInventory.getPetMap(); 
    
    if (petMap != null) {
      for (List<Livestock> animals : petMap.values()) {
        for (Livestock animal : animals) {
          int oldAge = animal.getAge();
          animal.growLivestock();
          if (animal.getAge() > oldAge) {
            grewCount++;
          }
        }
      }
    }
  }




  // choice 1
  public void feedAllAnimals(Scanner scanner, Player player) {
    System.out.println("\n──────────────── FEED ANIMALS ────────────────\n");
    if (livestockInventory.showAllItem().isEmpty()) {
      System.out.println("You have no livestock to feed.");
      InputUtils.waitEnter(scanner);
      return;
    }

    while (true) {
      System.out.print("Feed all animals? (y/n): ");
      String choice = scanner.next().trim().toLowerCase();

      if (choice.equals("y")) {
        int fedCount = 0;
        Map<String, List<Livestock>> petMap = livestockInventory.getPetMap();
        
        if (petMap != null) {
          for (List<Livestock> animals : petMap.values()) {
            for (Livestock animal : animals) {
              animal.feed();
              fedCount++;
            }
          }
        }
        
        System.out.println("\n[Success] All " + fedCount + " animals have been fed!");
        player.gainXP(fedCount * 2); 
        break;
      } else if (choice.equals("n")) {
        System.out.println("\nNo animals were fed.");
        break;
      } else {
        System.out.println("[Error] Please enter 'y' or 'n'.");
      }
    }
  InputUtils.waitEnter(scanner);
  System.out.println();
  }




  // choice 2
  public void breedAnimal(Scanner scanner, Player player) {
    System.out.println("\n──────────────── BREED ANIMALS ────────────────\n");
    List<Livestock> allAnimals = livestockInventory.getAllLivestock();
    if (livestockInventory.showAllItem().isEmpty()) {
      System.out.println("You have no livestock to breed.");
      InputUtils.waitEnter(scanner);
      return;
    }
    
    List<Livestock> breedableAnimals = new ArrayList<>();
    int index = 1;

    //  1. filter animals ready to breed
    System.out.println("Animals ready to breed (Age 8+ and Off Cooldown):");
    for (Livestock animal : allAnimals) {
      // animal is 'Adult' and breedCooldown is 0
      if (animal.getGrowthStage().equals("Adult") && animal.getAge() >= 8 && animal.breedCooldown == 0) { 
        System.out.printf("[%d] %-12s (%s)\n", index, animal.getPetName(), animal.getName());
        breedableAnimals.add(animal);
        index++;
      }
    }
    if (breedableAnimals.isEmpty()) {
        System.out.println("\nNo adult animals are ready to breed today (check age and cooldown).");
        InputUtils.waitEnter(scanner);
        return;
    }

    System.out.println("\nWhich animal would you like to breed? (Enter Number, [0] to cancel)");
    System.out.print("-> ");
    
    int breedChoice = InputUtils.getValidIntInput(scanner, 0, breedableAnimals.size());
    if (breedChoice == 0) {
      System.out.println("Breeding cancelled.");
      InputUtils.waitEnter(scanner);
      return;
    }

    Livestock parent = breedableAnimals.get(breedChoice - 1);

    //  2. prompt for baby name
    System.out.print("\nEnter a name for the new baby " + parent.getName() + ": ");
    System.out.println();
    String babyName = scanner.nextLine().trim();

    //  3. Call breed method to get the new baby instance
    Livestock babyAnimal = parent.breed(babyName);
    
    //  4. addAnimal() to the inventory
    if (babyAnimal != null) {
      player.getLivestockInventory().addAnimal(babyAnimal);
      player.gainXP(50);
      parent.setBreedCooldown(7);
      System.out.println("\n[Success] Breeding successful! " + babyName + " is now in your barn.");
    } else {
      System.out.println("\nBreeding failed. The mother was not ready.");
    }
    InputUtils.waitEnter(scanner);
  }




  //  choice 3
  public void collectProduceFromAnimals(Scanner scanner, Player player) {
    System.out.println("\n─────────────── COLLECT PRODUCE ───────────────\n");
    if (livestockInventory.showAllItem().isEmpty()) {
      System.out.println("You have no livestock to collect produce from.");
      InputUtils.waitEnter(scanner);
      return;
    }
    int totalProduceCollected = 0;
    Map<String, List<Livestock>> petMap = livestockInventory.getPetMap();
    
    if (petMap != null) {
      for (List<Livestock> animals : petMap.values()) {
        for (Livestock animal : animals) {
          int quantity = animal.collectProduce(); // collect produce adult and fed
          if (quantity > 0) {
            // produce name is retrieved from the animal
            produceInventory.addItem(animal.getProduce(), quantity); 
            totalProduceCollected += quantity;
          }
        }
      }
    }
    
    if (totalProduceCollected > 0) {
      System.out.println("\n[Success] You collected a total of " + totalProduceCollected + " produce!");
      player.gainXP(totalProduceCollected * 5); 
    } else {
      System.out.println("\nNo produce today.");
    }

    InputUtils.waitEnter(scanner);
    System.out.println();
  }
}