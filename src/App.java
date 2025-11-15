import base.*;
import itemEntities.fish.CaughtFish;
import itemEntities.fish.FishingSystem;

public class App {
    public static void main(String[] args) throws Exception {
        
        // trial
        Player player = new Player("fatima", "lemonFarm");
        GameManager game = new GameManager(player);

        System.out.println(player.getIslandName());
        player.nextDay();
        game.nextDay();

        CaughtFish fish = FishingSystem.catchFish("Spring");
        fish.sell();
    }

}
