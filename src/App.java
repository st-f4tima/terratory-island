import base.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        // trial
        Player player = new Player("fatima", "lemonFarm");
        GameManager game = new GameManager(player);

        System.out.println(player.getIslandName());
        player.nextDay();
        game.nextDay();


    }

}
