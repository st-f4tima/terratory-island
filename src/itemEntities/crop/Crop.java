package itemEntities.crop;

import base.Item;

// not final
public abstract class Crop extends Item {
  protected String season;
  protected int growthDays;
  protected boolean isWatered;
  protected boolean isFertilized;
  protected boolean isHarvested;

  public Crop(String name, int cost, int unlockLevel, String season, int growthDays, boolean isWatered, boolean isFertilized) {
    super(name, cost, unlockLevel);
    this.growthDays = growthDays;
    this.isWatered = isWatered;
    this.isFertilized = isFertilized;
    this.isHarvested = false;
    
  }

  // methods not final
  public void plantSeed() {
    System.out.println("You planted a " + getName() + " seed!");
  }
  public void waterPlant() {
    if (!isWatered) {
      isWatered = true;
      System.out.println("The crops have been watered.");
    } else {
      System.out.println("The crops are already watered.");
    }
  }

  public void fertilizePlant() {
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
      System.out.println("You can’t sell an unharvested crops!");
      return 0;
    }
  }
}
