package itemEntities.fish;

import itemEntities.fish.fall.*;
import itemEntities.fish.spring.*;
import itemEntities.fish.summer.*;
import itemEntities.fish.winter.*;
import java.util.Random;

public class FishingSystem {
    private static final Random random = new Random();

    public static Fish[] getFishForSeason(String season) {
        switch (season) {
            case "Fall":
                return new Fish[] {new Cod(), new Salmon(), new Trout()};
            case "Spring":
                return new Fish[] {new Anchovy(), new Catfish(), new Mullet()};
            case "Summer":
                return new Fish[] {new Mackerel(), new Sardines(), new Tuna()};
            case "Winter":
                return new Fish[] {new Perch(), new Pollock(), new Smelt()};
            default:
                return new Fish[] {};
        }
    }

    public static CaughtFish catchFish(String currentSeason) {
        Fish[] availableFish = getFishForSeason(currentSeason);

        if (availableFish.length == 0) {
            System.out.println("No fish available this season!");
            return null;
        }

        Fish caughtFishType = availableFish[random.nextInt(availableFish.length)];

        int weight = random.nextInt(10) + 1;

        CaughtFish caughtFish = new CaughtFish(caughtFishType, weight);
        System.out.println("You caught a " + caughtFish.getName() + " weighing " + weight + " kg!");
        
        return caughtFish;
    }
}