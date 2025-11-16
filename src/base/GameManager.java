package base;

import java.util.Scanner;

import managers.CropManager;

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

    while (true) {
      System.out.print("\n-> ");
      int menuChoice = scanner.nextInt();

      if (menuChoice == 1) {
        checkFarmFields(scanner); // Fatima
        break;
      } else if (menuChoice == 2) {
        // visitAnimalBarn(); - Gema
        break;
      } else if (menuChoice == 3) {
        // goFishing(); - Kishean
        break;
      } else if (menuChoice == 4) {
        // openInventory();
      } else if (menuChoice == 5) {
        //  viewProfile();
        break;
      } else if (menuChoice == 6) {
        // proceedToNextDay();
        break;
      } else if (menuChoice == 7) {
        System.out.println("Thank you!"); // not final msg
        return;
      } else {
        System.out.println("Error: Please select a number from 1 to 7.");
      }
    }
  }

  public void checkFarmFields(Scanner scanner) {
    System.out.println("\n──────────────── FARM FIELDS ────────────────\n");
    System.out.println("\"What a great day! What would you like to do in the farm fields?\"\n");
    System.out.println("1. Plant New Seeds");
    System.out.println("2. Apply Fertilizer");
    System.out.println("3. Water Your Crops");
    System.out.println("4. Harvest Your Crops");

    while (true) {
      System.out.print("Select 1 - 4, or 'm' to return to the main menu: ");
      char farmMenuChoice = Character.toLowerCase(scanner.next().charAt(0));
      scanner.nextLine(); // clears leftover newline hehe, i dont know

      CropManager cropManager = new CropManager();
      if (farmMenuChoice == '1') {
        cropManager.displayAvailableCrops(currentSeason);
        break;
      }
      // will do in later commits
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

