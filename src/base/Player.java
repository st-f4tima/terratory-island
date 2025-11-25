package base;

import java.util.HashMap;
import java.util.Map;

import inventoryEntities.CropInventory;
import inventoryEntities.LivestockInventory;

public class Player {
  private String username;
  private String islandName;
  private int level;
  private int xp;
  private int coins;
  private int dayCount;
  private CropInventory cropInventory;
  private LivestockInventory livestockInventory;
  private Map<String, Integer> fishInventory;

  public Player(String username, String islandName) {
    this.username = username;
    this.islandName = islandName;
    this.level = 1;
    this.xp = 0;
    this.coins = 0;
    this.dayCount = 1;
    this.cropInventory = new CropInventory();
    this.livestockInventory = new LivestockInventory();
    this.fishInventory = new HashMap<>();
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
  
  // methods
  public void gainXP(int amount) {
    this.xp += amount;
    checkLevelUp();
  }

  public void gainCoins(int amount) {
    this.coins += amount;
  }

  public void nextDay() {
    this.dayCount++;
  }

  public int getRequiredXP() {
    return 10 * this.level;
  }

  private void checkLevelUp() {
    while (this.xp >= getRequiredXP()) {
      this.xp -= getRequiredXP();
      this.level++;
      System.out.println("Congratulations! You reached Level " + this.level + "!");
    }
  }
}
