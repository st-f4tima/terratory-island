package base;

import java.util.Scanner;

public class GameManager {
  private Player player;
  private String currentSeason;
  
  public GameManager() {
    this.currentSeason = "Spring";
  }

  public static boolean displayIntro(Scanner scanner) {
    final String GREEN = "\u001B[32m";
    final String RESET = "\u001B[0m";

    System.out.println();
    System.out.println(GREEN + """
      ██████ ██████ █████▄  █████▄  ▄████▄ ██████ ▄████▄ █████▄  ██  ██   ██ ▄█████ ██     ▄████▄ ███  ██ ████▄  
        ██   ██▄▄   ██▄▄██▄ ██▄▄██▄ ██▄▄██   ██   ██  ██ ██▄▄██▄  ▀██▀    ██ ▀▀▀▄▄▄ ██     ██▄▄██ ██ ▀▄██ ██  ██ 
        ██   ██▄▄▄▄ ██   ██ ██   ██ ██  ██   ██   ▀████▀ ██   ██   ██     ██ █████▀ ██████ ██  ██ ██   ██ ████▀  
    """ + RESET);
    System.out.println("\"You sail alone across the vast ocean. Suddenly, a violent storm engulfs you.\n" +
    "Swept by the raging waves, you are cast onto the shore of an unknown island.\"");

    while (true) {
      System.out.print("\nExplore the island? (y/n): ");
      char answer = Character.toLowerCase(scanner.next().charAt(0));
      scanner.nextLine(); // clears leftover newline hehe, i dont know

      if (answer == 'y') {
        return true;
      } else if (answer == 'n') {
        System.out.println("\n\"You decide against exploring.\nThe storm settles, and you set sail again.\"\n");
        return false;
      } else {
        System.out.println("Invalid Input.");
      }
    }
  }

  public void createCharacter(Scanner scanner) {
    System.out.println("\n───────────── BUILD CHARACTER ─────────────");

    System.out.print("\nEnter username: ");
    String username = scanner.nextLine();

    System.out.print("Enter island name: ");
    String islandName = scanner.nextLine();

    this.player = new Player(username, islandName);

    System.out.println("\nCharacter created successfully!");
  }

  public void displayMenu(Scanner scanner) {
    // not final
    System.out.println("\n──────────────── MAIN MENU ────────────────");
    System.out.println("\nWelcome, " + player.getUsername() + " of " + player.getIslandName() + "!");
    System.out.println("Day " + player.getDayCount() + " - Season: " + currentSeason);

    System.out.println("\nWhat would you like to do today?");

    System.out.printf("\n%-25s %-25s %-25s\n",
      "--- Activities ---",
      "--- Management ---",
      "--- System ---"
    );

    System.out.printf("%-25s %-25s %-25s\n",
      "1. Check Farm Fields", 
      "4. Open Inventory", 
      "6. Proceed to Next Day"
    );

    System.out.printf("%-25s %-25s %-25s\n",
      "2. Visit Animal Barn", 
      "5. View Profile", 
      "7. Leave Game"
    );

    System.out.printf("%-25s %-25s %-25s\n",
      "3. Go Fishing", 
      "", 
      ""
    );

    System.out.print("\n-> ");
    
    // switch case here
  }

  public void nextDay() {
    player.nextDay();
    updateSeason();
    System.out.println("Day " + player.getDayCount() + " - Season: " + currentSeason);
  }

  private void updateSeason() {
    int day = player.getDayCount() % 112;

    if (day <= 28) {
      this.currentSeason = "Spring";
    } else if (day <= 56) {
      this.currentSeason = "Summer";
    } else if (day <= 84) {
      this.currentSeason = "Fall";
    } else {
      this.currentSeason = "Winter";
    }
  }
}

