package itemEntities.fish;

import base.Item;
import java.util.Random;

public abstract class Fish extends Item {
    protected String season;
    protected int weight;
    protected boolean isCatched;

    public Fish (String name, int cost, int unlockLevel, String season, int weight){
        super (name, cost, unlockLevel);
        this.season = season;
        this.weight = weight;
        this.isCatched = false;
    }

    public void catchFish (){
        Random rand = new Random();

        this.weight = rand.nextInt(10) + 1;
        this.isCatched = true;

        System.out.println("You caught a " + getName() + " weighing " + weight + " kg!");
    }

    public int sell () {
        if (isCatched) {
            int sellPrice = (int)(getCost() * weight);
            System.out.println("You sold a " + getName() + " for " + sellPrice + " coins!");
            isCatched = false;
            return sellPrice;
        } else {
            System.out.println("You haven't caught any fish yet.");
            return 0;
        } 
    }

}

