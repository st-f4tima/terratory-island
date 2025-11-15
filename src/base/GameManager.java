package base;

import java.util.Scanner;

public class GameManager {
  public static boolean startGame = false;
  private Player player;
  private String currentSeason;
  
  public GameManager(Player player) {
    this.player = player;
    this.currentSeason = "Spring";
  }

  public static void showIntro() {
    Scanner scanner = new Scanner(System.in);

    String GREEN = "\u001B[32m";
    String RESET = "\u001B[0m";

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

      if (answer == 'y') {
        startGame = true;
        break;
      } else if (answer == 'n') {
        startGame = false;
        System.out.println("\n\"You decide against exploring.\nThe storm settles, and you set sail again.\"\n");
        break;
      } else {
        System.out.println("Invalid Input.");
      }
    }
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

