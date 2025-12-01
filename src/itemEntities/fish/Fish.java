package itemEntities.fish;

import base.Item;

public abstract class Fish extends Item {
  protected String season;
  protected int maxWeight;
  protected int caughtFishWeight;

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

  public int getcaughtFishWeight(){
    return caughtFishWeight;
  }

  public void setCaughtFishWeight(int weight){
    this.caughtFishWeight = weight;
  }

  @Override
  public int sell() {
    int sellPrice = getCost() * getcaughtFishWeight();
    System.out.println("You sell an/a " + this.getName() + " for a price of " + sellPrice + ".");
    return sellPrice;
  }
}
