package inventoryEntities;

import base.Inventory;
import itemEntities.animal.Livestock;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void viewData(){
        System.out.println("\n───────────── INVENTORY : LIVESTOCK ─────────────");
        if(this.pet.isEmpty()){
            System.out.println("You do not have any animal friends right now.");
            return;
        }

        for(Map.Entry<String, List<Livestock>> entry : this.pet.entrySet()){
            String species = entry.getKey();
            List<Livestock> animals = entry.getValue();
            System.out.println("-> " +species +" (" +animals.size() +"): ");

            System.out.println("+" + "-".repeat(41) + "+");
            for (Livestock animal : animals){
                String status = String.format("| %-12s | %-8s | %4d days old |",
                    animal.getPetName(),
                    animal.getGrowthStage().toLowerCase(),
                    animal.getAge());
                System.out.println(status);
            }
            System.out.println("+" + "-".repeat(41) + "+");
        }
    }
    
}