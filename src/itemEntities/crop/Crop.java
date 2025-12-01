package itemEntities.crop;

import base.Item;

public abstract class Crop extends Item {
  protected String season;
  protected int growthDays;
  protected int daysGrown;
  protected int yieldAmount;
  protected boolean isWatered;
  protected boolean isFertilized;
  protected boolean isHarvested;

  public Crop(String name, int cost, int levelRequired, String season, int growthDays, boolean isWatered, boolean isFertilized) {
    super(name, cost, levelRequired);
    this.season = season;
    this.growthDays = growthDays;
    this.daysGrown = 1;
    this.yieldAmount = 16;
    this.isWatered = isWatered;
    this.isFertilized = isFertilized;
    this.isHarvested = false;
  }

  public abstract Crop createCopy();

  // getters
  public int getYieldAmount() {
    return yieldAmount;
  }

  public String getSeason() {
    return season;
  }

  public int getGrowthDays() {
    return growthDays;
  }

  public int getDaysGrown() {
    return daysGrown;
  }

  public boolean isWatered() {
    return isWatered;
  }

  public boolean isFertilized() {
    return isFertilized;
  }

  public boolean isHarvested() {
    return isHarvested;
  }

  // methods
  public void water() {
    isWatered = true;
  }

  public void applyFertilizer() {
    isFertilized = true;
  }

  public void grow() {
    daysGrown++;
  }

  // Watering and fertilizing reset each day
  public void resetDailyCare() {
    this.isWatered = false;
    this.isFertilized = false;
  }

  public void harvest() {
    isHarvested = true;
  }

  public boolean isHarvestable() {
    return daysGrown >= growthDays && !isHarvested;
  }
  
  @Override
  public int sell() {
    if (isHarvested) {
      int sellPrice = (int)(getCost() * 1.5);
      System.out.println("\nYou sold a " + getName() + " for $" + sellPrice + " coins!");
      return sellPrice;
    } else {
      System.out.println("\nYou can't sell an unharvested crop!");
      return 0;
    }
  }
}