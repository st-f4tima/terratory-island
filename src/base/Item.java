package base;

// not final
public abstract class Item {
  private String name;
  private int cost;
  private int unlockLevel;

  public Item(String name, int cost, int unlockLevel) {
    this.name = name;
    this.cost = cost;
    this.unlockLevel = unlockLevel;
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

  // setters (not yet sure if gustong nyong naeedit sila)

}
