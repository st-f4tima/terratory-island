package base;

import java.util.HashMap;
import java.util.Map;

public abstract class Inventory {
    protected Map<String, Integer> itemCount;

    public Inventory() {
        this.itemCount = new HashMap<>();
    }

    //  adds item to inventory
    public void addItem(String itemName, int quantity) {
        if(quantity < 1) return;

        int currentQ = this.itemCount.getOrDefault(itemName, 0);
        this.itemCount.put(itemName, currentQ + quantity);
        System.out.println("\nCollected " +quantity +" " +itemName +"/s.");
    }

    // map of all items in store
    public Map<String, Integer> showAllItem(){
        return new HashMap<>(this.itemCount);
    }

    // display per inventory
    public abstract void viewData();
}