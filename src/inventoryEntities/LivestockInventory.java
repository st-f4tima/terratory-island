package inventoryEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import base.Inventory;
import base.Player;
import itemEntities.animal.Livestock;
import utils.InputUtils;

public class LivestockInventory extends Inventory{
  private Map<String, List<Livestock>> pet;

  public LivestockInventory(){
    super();
    this.pet = new HashMap<>();
  }

  public void addAnimal(Livestock animal){
    String species = animal.getName();
    this.pet.computeIfAbsent(species, k -> new ArrayList<>()).add(animal);
    /*
      *computeIfAbsent(species, ...)   
        Checks if the species key exists in the map.
      *k -> new ArrayList<>()
        If the species key doesn't exist yet, 
        it creates a brand new ArrayList (a list to hold the animals) 
        and puts it in the map under that key.
      *.add(animal)                    
        After the List is retrieved or created, 
        the new animal is added to it.
    */

    super.addItem(species, 1);
    System.out.println(animal.getPetName() +" (" +species +") added to inventory.");
  }

  public List<Livestock> getAllLivestock() {
    List<Livestock> allAnimals = new ArrayList<>();
    for (List<Livestock> animals : this.pet.values()) {
        allAnimals.addAll(animals);
    }
    return allAnimals;
  }

  public boolean removeAnimal(Livestock animal) {
    String species = animal.getName();
    List<Livestock> animals = this.pet.get(species);
    
    if (animals != null && animals.remove(animal)) {
      int currentCount = this.itemCount.getOrDefault(species, 0);
      
      if (currentCount > 1) {
        this.itemCount.put(species, currentCount - 1);
      } else if (currentCount == 1) {
        this.itemCount.remove(species);
        this.pet.remove(species);
      } 
      return true;
    }
    return false;
  }

  public void sellOneLivestock(Scanner scanner, Player player) {
    System.out.println("\n───────────────── SELL LIVESTOCK ─────────────────\n");
    
    List<Livestock> allAnimals = getAllLivestock();
    
    if (allAnimals.isEmpty()) {
      System.out.println("You have no livestock to sell.");
      return;
    }

    viewData();
    System.out.println("\nWhich animal would you like to sell? (Enter Number)");
    System.out.println("[0] Cancel Sale");
    System.out.print("-> ");
    
    int sellChoice = InputUtils.getValidIntInput(scanner, 0, allAnimals.size());
    
    if (sellChoice == 0) {
      System.out.println("Sale cancelled.");
      return;
    }

    // get chosen animal (index is 1-based)
    Livestock animalToSell = allAnimals.get(sellChoice - 1);
    int coinsEarned = animalToSell.sell();

    if (coinsEarned > 0) {
      player.gainCoins(coinsEarned);
      player.gainXP(20); 
      
      // remove animal from inventory
      if (!removeAnimal(animalToSell)) {
        System.out.println("\n[Error] Could not remove animal from inventory.");
      }
    }
  }

  public Map<String, List<Livestock>> getPetMap() {
    return this.pet;
  }

  @Override
  public void viewData(){
    System.out.println("\n───────────── INVENTORY : LIVESTOCK ─────────────");
    if(this.pet.isEmpty()){
      System.out.println("You do not have any animal friends right now.");
      return;
    }

    int animalIndex = 1; 
    for(Map.Entry<String, List<Livestock>> entry : this.pet.entrySet()){
      String species = entry.getKey();
      List<Livestock> animals = entry.getValue();
      
      
      System.out.println("-> " + species + " (" + animals.size() + "): ");
      System.out.println("+" + "-".repeat(47) + "+"); // Increased width for the index
      
      for (Livestock animal : animals){
        String status = String.format("| %3d | %-12s | %-8s | %4d days old |",
          animalIndex, // The consecutive index
          animal.getPetName(),
          animal.getGrowthStage().toLowerCase(),
          animal.getAge());
        System.out.println(status);
        animalIndex++; 
      }
      System.out.println("+" + "-".repeat(47) + "+");
    }
  }
}