import base.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean startGame = GameManager.displayIntro(scanner);
        if (startGame) {
            GameManager game = new GameManager();
            game.createCharacter(scanner);
            game.displayMenu(scanner);
        }

        scanner.close();
    }

}
