package itemEntities.fish;

import base.Item;

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

    // delete, put it inside sell()
    public int calculateSellPrice(int weight) {
        return getCost() * weight;
    }

    @Override
    public int sell() {
        // fix logic
        return 0;
    }
}
