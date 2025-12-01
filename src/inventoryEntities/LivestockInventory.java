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

  // sell animal
  public void sellOneLivestock(Scanner scanner, Player player) {
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

  //  getter
  public Map<String, List<Livestock>> getPetMap() {
    return this.pet;
  }

  @Override
  public void viewData() {
    System.out.println("\n─────────────── INVENTORY : LIVESTOCK ───────────────\n");
    if (this.pet.isEmpty()) {
      System.out.println("You don't have any livestock yet.");
      return;
    }

    String topLine    = "┌─────┬──────────────┬──────────┬──────────────┐";
    String header     = "│ No. │     Name     │  Stage   │     Age      │";
    String separator  = "├─────┼──────────────┼──────────┼──────────────┤";
    String bottomLine = "└─────┴──────────────┴──────────┴──────────────┘";

    int animalIndex = 1;

    System.out.println(topLine);
    System.out.println(header);

    boolean firstSpecies = true;
    for(Map.Entry<String, List<Livestock>> entry : this.pet.entrySet()){
      String species = entry.getKey();
      List<Livestock> animals = entry.getValue();

      if (!firstSpecies) {
        System.out.println(separator);
      }
      firstSpecies = false;

      String speciesHeader = String.format("│ %-40s │", "Species: " + species + " (" + animals.size() + ")");
      System.out.println(speciesHeader);
      System.out.println(separator);

      for (Livestock animal : animals){
        String row = String.format(
          "│ %-3d │ %-12s │ %-8s │%3d days old │",
          animalIndex,
          animal.getPetName(),
          animal.getGrowthStage().toLowerCase(),
          animal.getAge()
        );
        System.out.println(row);
        animalIndex++;
      }
    }
    System.out.println(bottomLine);
  }
}