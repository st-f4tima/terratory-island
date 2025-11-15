import base.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // not final
        Scanner scanner = new Scanner(System.in);
        GameManager.showIntro();
        if (GameManager.startGame) {
            System.out.print("\nEnter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter island name: ");
            String islandName = scanner.nextLine();

            Player player = new Player(username, islandName);
            GameManager game = new GameManager(player);
        }

        scanner.close();
    }
}
