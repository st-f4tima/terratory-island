package base;

// not final
public abstract class Item {
  private String name;
  private int cost;
  private int unlockLevel;
  private int xp;

  public Item(String name, int cost, int unlockLevel, int xp) {
    this.name = name;
    this.cost = cost;
    this.unlockLevel = unlockLevel;
    this.xp = xp;
  }

  public abstract int sell();

  // getters
  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  public int getUnlockLevel() {
    return unlockLevel;
  }

  public int xp() {
    return xp;
  }

  // setters (not yet sure if gustong nyong naeedit sila)

}
