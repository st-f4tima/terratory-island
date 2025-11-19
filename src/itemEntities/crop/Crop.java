package itemEntities.crop;

import base.Item;

public abstract class Crop extends Item {
  protected String season;
  protected int growthDays;
  protected int yieldAmount;
  protected boolean isWatered;
  protected boolean isFertilized;
  protected boolean isHarvested;

  public Crop(String name, int cost, int levelRequired, String season, int growthDays, boolean isWatered, boolean isFertilized) {
    super(name, cost, levelRequired);
    this.season = season;
    this.growthDays = growthDays;
    this.yieldAmount = 16;
    this.isWatered = isWatered;
    this.isFertilized = isFertilized;
    this.isHarvested = false;
  }

  public abstract Crop createCopy();

  // Getters
  public int getYieldAmount() {
    return yieldAmount;
  }

  public String getSeason() {
    return season;
  }

  public int getGrowthDays() {
    return growthDays;
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

  // Methods
  public void waterCrops() {
    if (!isWatered) {
      isWatered = true;
      System.out.println("The crops have been watered.");
    } else {
      System.out.println("The crops are already watered.");
    }
  }

  public void applyFertilizer() {
    if (!isFertilized) {
      isFertilized = true;
      System.out.println("The crops have been fertilized.");
    } else {
      System.out.println("The crops are already fertilized.");
    }
  }

  public void harvest() {
    if (isWatered && isFertilized) {
      isHarvested = true;
      System.out.println("You harvested a " + getName() + "!");
    } else {
      System.out.println("The crops aren't ready for harvest yet.");
    }
  }
  
  @Override
  public int sell() {
    if (isHarvested) {
      int sellPrice = (int)(getCost() * 1.5);
      System.out.println("You sold a " + getName() + " for " + sellPrice + " coins!");
      return sellPrice;
    } else {
      System.out.println("You can't sell an unharvested crop!");
      return 0;
    }
  }
}