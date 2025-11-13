package base;

import java.util.HashMap;
import java.util.Map;

public class Player {
  private String username;
  private String islandName;
  private int level;
  private int xp;
  private int coins;
  private int dayCount;
  private Map<String, Integer> cropInventory;
  private Map<String, Integer> livestockInventory;
  private Map<String, Integer> fishInventory;

  public Player(String username, String islandName) {
    this.username = username;
    this.islandName = islandName;
    this.level = 1;
    this.xp = 0;
    this.coins = 0;
    this.dayCount = 1;
    this.cropInventory = new HashMap<>();
    this.livestockInventory = new HashMap<>();
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

  public Map<String, Integer> getCropInventory() {
    return this.cropInventory;
  }

  public Map<String, Integer> getLivestockInventory() {
    return this.livestockInventory;
  }

  public Map<String, Integer> getFishInventory() {
    return this.fishInventory;
  }

  // methods
  public void gainXP(int amount) {
    this.xp += amount;
  }

  public void gainCoins(int amount) {
    this.coins += amount;
  }

  public void nextDay() {
    this.dayCount++;
  }
}
