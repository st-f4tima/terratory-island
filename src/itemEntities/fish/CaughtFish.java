package itemEntities.fish;

public class CaughtFish {
    private Fish fishType;
    private int weight;

    public CaughtFish(Fish fishType, int weight) {
        this.fishType = fishType;
        this.weight = weight;
    }

    public Fish getFishType() {
        return fishType;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return fishType.getName();
    }

    public int sell() {
        int sellPrice = fishType.calculateSellPrice(weight);
        System.out.println("You sold a " + getName() + " (" + weight + " kg) for " + sellPrice + " coins!");
        return sellPrice;
    }

    @Override
    public String toString() {
        return getName() + " (" + weight + " kg)";
    }
}

