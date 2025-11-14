package itemEntities.fish;

import base.Item;
import java.util.Random;

public abstract class Fish extends Item {
    protected String season;
    protected int weight;
    protected boolean isCatched;
    protected String caughtFish;

    public Fish (String name, int cost, int unlockLevel, String season, int weight){
        super (name, cost, unlockLevel);
        this.season = season;
        this.weight = weight;
        this.isCatched = false;
        this.caughtFish = null;
    }

    public void catchFish (String currentSeason){
        Random rand = new Random();

        String[] fishList;
        switch(currentSeason){
            case "Fall":
                fishList = new String[] {"Cod", "Salmon", "Trout"};
                break;
            case "Spring":
                fishList = new String[] {"Anchovy", "Catfish", "Mullet"};
                break;
            case "Summer":
                fishList = new String[] {"Mackerel", "Sardines", "Tuna"};
                break;
            case "Winter":
                fishList = new String[] {"Perch", "Pollock", "Smelt"};
                break;
            default:
                fishList = new String[] {};
        }

        if(fishList.length == 0){
            System.out.println("No fish available this season!");
            return;
        }

        this.caughtFish = fishList[rand.nextInt(fishList.length)];
        this.weight = rand.nextInt(10) + 1;
        this.isCatched = true;
        this.season = currentSeason;

        System.out.println("You caught a " + getName() + " weighing " + weight + " kg!");
    }

    public int sell () {
        if (isCatched) {
            int sellPrice = (int)(getCost() * weight);
            System.out.println("You sold a " + getName() + " for " + sellPrice + " coins!");
            isCatched = false;
            caughtFish = null;
            return sellPrice;
        } else {
            System.out.println("You haven't caught any fish yet.");
            return 0;
        } 
    }

}

