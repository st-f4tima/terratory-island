package managers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import base.Player;
import utils.InputUtils;
import inventoryEntities.ProduceInventory;
import inventoryEntities.LivestockInventory;
import itemEntities.animal.Livestock;

public class LivestockManager {
  private LivestockInventory livestockInventory;
  private ProduceInventory produceInventory;

  public LivestockManager(LivestockInventory animalInv, ProduceInventory produceInv) {
      this.livestockInventory = animalInv;
      this.produceInventory = produceInv;
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
    if (livestockInventory.showAllItem().isEmpty()) {
      System.out.println("You have no livestock to breed.");
      InputUtils.waitEnter(scanner);
      return;
    }
    
    // Note: Full breeding logic requires animal selection which is complex for a simple menu.
    System.out.println("Breeding is a bit complex for a simple menu...");
    System.out.println("For now, this feature is not fully implemented.");
    
    InputUtils.waitEnter(scanner);
    System.out.println();
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