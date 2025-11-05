package itemCategory;

import base.Item;

public class Crop extends Item {
  protected String season;
  protected int growthDays;
  protected boolean isWatered;
  protected boolean isFertilized;
  protected boolean isHarvested;

  public Crop(String name, int cost, int unlockLevel, int xp, String season, int growthDays, boolean isWatered, boolean isFertilized) {
    super(name, cost, unlockLevel, xp);
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
      System.out.println("The plant has been watered.");
    } else {
      System.out.println("The plant is already watered.");
    }
  }

  public void fertilizePlant() {
    if (!isFertilized) {
      isFertilized = true;
      System.out.println("The plant has been fertilized.");
    } else {
      System.out.println("The plant is already fertilized.");
    }
  }

  public void harvest() {
    if (isWatered && isFertilized) {
      isHarvested = true;
      System.out.println("You harvested a " + getName() + "!");
    } else {
      System.out.println("The crop isn't ready for harvest yet.");
    }
  }

  public int sell() {
  if (isHarvested) {
    int sellPrice = (int)(getCost() * 1.5);
    System.out.println("You sold a " + getName() + " for " + sellPrice + " coins!");
    return sellPrice;
  } else {
    System.out.println("You can’t sell an unharvested crop!");
    return 0;
  }
}


}
