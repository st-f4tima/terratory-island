import itemEntities.crop.spring.*;
import base.*;

public class App {
    public static void main(String[] args) throws Exception {
        Potato potato = new Potato();
        potato.plantSeed();

        Player player = new Player("fatima", "lemonFarm", 0, 0);
        System.out.println(player.getIslandName());

    }
}
