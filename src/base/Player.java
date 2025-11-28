package base;

import inventoryEntities.CropInventory;
import inventoryEntities.FishInventory;
import inventoryEntities.LivestockInventory;
import inventoryEntities.ProduceInventory;

public class Player {
  private String username;
  private String islandName;
  private int level;
  private int xp;
  private int coins;
  private int dayCount;
  private CropInventory cropInventory;
  private FishInventory fishInventory;
  private LivestockInventory livestockInventory;
  private ProduceInventory produceInventory;

  public Player(String username, String islandName) {
    this.username = username;
    this.islandName = islandName;
    this.level = 1;
    this.xp = 0;
    this.coins = 0;
    this.dayCount = 1;
    this.cropInventory = new CropInventory();
    this.livestockInventory = new LivestockInventory();
    this.fishInventory = new FishInventory();
  }

  // getters
  public String getUsername() {
    return this.username;
  }

  public String getIslandName() {
    return this.islandName;
  }

  public int getLevel() {
    return this.level;
  }

  public int getCoins() {
    return this.coins;
  }

  public int getXP() {
    return this.xp;
  } 

  public int getDayCount() {
    return this.dayCount;
  }

  public CropInventory getCropInventory() {
    return this.cropInventory;
  }

  public FishInventory getFishInventory() {
    return this.fishInventory;
  }

  public LivestockInventory getLivestockInventory() {
    return this.livestockInventory;
  }

  public ProduceInventory getProduceInventory() {
    return this.produceInventory;
  }
  
  // methods
  public void gainXP(int amount) {
    this.xp += amount;
    System.out.println("\nYou gained " + amount + " XP (Total XP: " + this.xp + ").");
    checkLevelUp();
  }

  public void gainCoins(int amount) {
    this.coins += amount;
  }

  public void nextDay() {
    this.dayCount++;
  }

  public int getRequiredXP() {
    return (int) (10 * Math.pow(this.level, 2)); 
  }

  private void checkLevelUp() {
    while (this.xp >= getRequiredXP()) {
      this.xp -= getRequiredXP();
      this.level++;
      System.out.println("Congratulations! You reached Level " + this.level + "!");
    }
  }
}
