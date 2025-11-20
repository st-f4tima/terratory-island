package base;

import java.util.Scanner;
import managers.CropManager;
import managers.FishManager;

public class GameManager {
  private Player player;
  private String currentSeason;
  private CropManager cropManager;
  private FishManager fishManager;
  
  public GameManager() {
    this.currentSeason = "Spring";
    this.cropManager = new CropManager();
    this.fishManager = new FishManager();
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
      String answer = scanner.nextLine().trim().toLowerCase();

      if (answer.equals("y")) {
        return true;
      } else if (answer.equals("n")) {
        System.out.println("\n\"You decide against exploring.\nThe storm settles, and you set sail again.\"\n");
        return false;
      } else {
        System.out.println("Invalid Input. Please enter 'y' or 'n'.");
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
    while (true) {
      System.out.println("\n──────────────── MAIN MENU ────────────────");
      System.out.println("\nWelcome, " + player.getUsername() + " of " + player.getIslandName() + "!");
      System.out.println("Day " + player.getDayCount() + " - Season: " + currentSeason);

      System.out.println("\n\"What would you like to do today?\"");

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
        "3. Visit Fishing Dock", 
        "", 
        ""
      );

      System.out.print("\n-> ");
      int menuChoice = scanner.nextInt();

      if (menuChoice == 1) {
        checkFarmFields(scanner); // Fatima
        continue;
      } else if (menuChoice == 2) {
        // visitAnimalBarn(); - Gema
      } else if (menuChoice == 3) {
        visitFishingDock(scanner);
        continue;
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
        System.out.println("[Error] Please select a number from 1 to 7.");
      }
    }
  }

  public void checkFarmFields(Scanner scanner) {
    while (true) {
      System.out.println("\n──────────────── FARM FIELDS ────────────────\n");
      System.out.println("\"Sun's out, tools out! What mischief-I mean, farming-shall we do today?\"\n");
      System.out.println("1. Plant New Seeds");
      System.out.println("2. Water Your Crops");
      System.out.println("3. Apply Fertilizer");
      System.out.println("4. Harvest Your Crops");
      System.out.println("5. I want to do something else");
      System.out.print("\n-> ");

      int farmMenuChoice = scanner.nextInt();
      scanner.nextLine(); 
      
      if (farmMenuChoice == 1) {
          cropManager.plantSeeds(currentSeason, player, scanner);
          continue;
        } else if (farmMenuChoice == 2) {
            cropManager.waterCrops(scanner);
            continue;
        } 
    }
  }

  // visit the animals
  public void visitAnimalBarn(Scanner scanner) {
    System.out.println("\n──────────────── ANIMAL BARN ───────────────\n");
    System.out.println("\"Time to check on your friends!~ What needs doing in the barn today?\"\n");
    System.out.println("1. Feed Animals");
    System.out.println("2. Breed Animals");
    System.out.println("3. Collect Produce");
    System.out.println("4. They're alright, I'll go do something else");

    while (true) { 
      System.out.print("\nSelect from 1-4: ");
      int barnMenuChoice = scanner.nextInt();

      /*  WIP
      LivestockManager livestockManager = scanner.nextInt();
      if (barnMenuChoice == 1) {
        
      }
      */
    }

  }

  public void visitFishingDock(Scanner scanner) {
    System.out.println("\n──────────────── FISHING DOCK ───────────────\n");
    System.out.println("\"Fishing time! Remember: patience is key, and so is random luck.\"\n");
    System.out.println("1. Start Fishing");
    System.out.println("2. I want to do something else");
    
    while (true) {
      System.out.print("\n-> ");
      int fishingMenuChoice = scanner.nextInt();

      if (fishingMenuChoice == 1) {
        fishManager.catchFish(currentSeason);
        break;
      } else if (fishingMenuChoice == 2) {
        displayMenu(scanner);
        break;
      } else {
        System.out.println("[Error] Please select a number from 1 to 2.");
      }
    }
  }

  // TODO: Implement OpenInventory()
  // TODO: Implement viewProfile()
  // TODO: Implment proceedToNextDay() - put nextDay() logic on it
  // TODO: Implement LeaveGame()

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

