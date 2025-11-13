import base.*;
import itemEntities.fish.Fish;
import itemEntities.fish.spring.Anchovy;

public class App {
    public static void main(String[] args) throws Exception {
        
        // trial
        Player player = new Player("fatima", "lemonFarm");
        GameManager game = new GameManager(player);

        System.out.println(player.getIslandName());
        player.nextDay();
        game.nextDay();

        Anchovy anchovy = new Anchovy();
        anchovy.catchFish();


    }
}
