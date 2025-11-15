package itemEntities.fish;

import base.Item;

public abstract class Fish extends Item {
    protected String season;
    protected int baseWeight;

    public Fish(String name, int baseCost, int unlockLevel, String season, int baseWeight) {
        super(name, baseCost, unlockLevel);
        this.season = season;
        this.baseWeight = baseWeight;
    }

    public String getSeason() {
        return season;
    }

    public int getBaseWeight() {
        return baseWeight;
    }

    public int calculateSellPrice(int weight) {
        return getCost() * weight;
    }

    @Override
    public int sell() {
        System.out.println("You cannot sell a fish template. Catch a fish first!");
        return 0;
    }
}
