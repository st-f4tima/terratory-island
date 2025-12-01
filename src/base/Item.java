package base;

public abstract class Item {
  private String name;
  private int cost;
  private int levelRequired;

  public Item(String name, int cost, int levelRequired ) {
    this.name = name;
    this.cost = cost;
    this.levelRequired = levelRequired;
  }

  public abstract int sell();

  // getters
  public String getName() {
    return name;
  }

  public int getCost() {
    return cost;
  }

  public int getLevelRequired() {
    return levelRequired;
  }
}
