package itemEntities.fish;

import base.Item;
import managers.FishManager;;

public abstract class Fish extends Item {
    protected String season;
    protected int maxWeight;

    public Fish(String name, int baseCost, int unlockLevel, String season, int maxWeight) {
        super(name, baseCost, unlockLevel);
        this.season = season;
        this.maxWeight = maxWeight;
    }

    public String getSeason() {
        return season;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    @Override
    public int sell() {
        int sellPrice = getCost() * maxWeight;
        System.out.println("You sell an/a " + this.getName() + " for a price of " + sellPrice);
        return sellPrice;
    }
}
