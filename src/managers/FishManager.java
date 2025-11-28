package managers;

import java.util.List;
import java.util.Random;
import itemEntities.fish.Fish;
import itemEntities.fish.fall.*;
import itemEntities.fish.spring.*;
import itemEntities.fish.summer.*;
import itemEntities.fish.winter.*;


public class FishManager {
    Random random;
    List<Fish> springFish;
    List<Fish> summerFish;
    List<Fish> fallFish;
    List<Fish> winterFish;

    public FishManager() {
        this.random = new Random();
        springFish = List.of(new Anchovy(), new Catfish(), new Mullet());
        summerFish = List.of(new Mackerel(), new Sardines(), new Tuna());
        fallFish = List.of(new Cod(), new Salmon(), new Trout());
        winterFish = List.of(new Perch(), new Pollock(), new Smelt());
    }

    public List<Fish> getFishPerSeason(String currentSeason) {
        List<Fish> availableFishes;
        
        switch (currentSeason) {
            case "Spring":
                availableFishes = springFish;
                break;
            case "Summer": 
                availableFishes = summerFish;
                break;
            case "Fall":
                availableFishes = fallFish;
                break;
            case "Winter":
                availableFishes = winterFish;
                break;
            default:
                System.out.println("Invalid season.");
                return null;
        }
        return availableFishes;
    }

    public Fish getRandomFish(String currentSeason) {
        List<Fish> availableFish = getFishPerSeason(currentSeason);
        
        int randomIndex = random.nextInt(availableFish.size());
        return availableFish.get(randomIndex);
    }

    public int getRandomWeight(Fish randomFish) {
        return random.nextInt((randomFish.getMaxWeight()) + 2); 
    }

    public void catchFish(String currentSeason) {
        System.out.println("\nCasting your line...");
        Fish caughtFish = getRandomFish(currentSeason);
        int caughtFishWeight = getRandomWeight(caughtFish);

        System.out.println("\nYou caught a " + caughtFish.getName() + " weighing " + caughtFishWeight + " kg!");

    }

}
