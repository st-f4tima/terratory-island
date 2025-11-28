package inventoryEntities;

import base.Inventory;
import java.util.HashMap;
import java.util.Map;

public class ProduceInventory extends Inventory{
    private Map<String, Integer> produce;

    public ProduceInventory() {
        super();
        this.produce = new HashMap<>();
    }

    @Override
    public void viewData() {
    System.out.println("\n───────────── INVENTORY : PRODUCE ─────────────");
    if(this.produce.isEmpty()){
    System.out.println("You do not have any animal friends right now.");
        return;
    }

    for(Map.Entry<String, Integer> entry : this.produce.entrySet()){
        String product = entry.getKey();
        Integer amount = entry.getValue();

        System.out.println("+" + "-".repeat(41) + "+");
        System.out.printf("| %-10s | %-3d |", product, amount);
        System.out.println("+" + "-".repeat(41) + "+");
    }
    }
}
