package base;

import java.util.Scanner;
import managers.CropManager;
import managers.FishManager;
import managers.InventoryManager;
import managers.LivestockManager;
import utils.InputUtils;

public class GameManager {
  private Player player;
  private String currentSeason;
  private CropManager cropManager;
  private FishManager fishManager;
  private LivestockManager livestockManager;
  
  public GameManager() {
    this.currentSeason = "Spring";
  }

  public static boolean displayIntro(Scanner scanner) {
    // final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String RESET = "\u001B[0m";

    System.out.println();
    System.out.println(YELLOW + """
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
        System.out.println("[Error] Invalid Input. Please enter 'y' or 'n'.");
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
    this.cropManager = new CropManager(player);
    this.fishManager = new FishManager();
    this.livestockManager = new LivestockManager(player.getLivestockInventory(), player.getProduceInventory());

    System.out.println("\nCharacter created successfully!");
    InputUtils.waitEnter(scanner);
    return;
  }

  public void displayMenu(Scanner scanner) {
    while (true) {
      System.out.println("\n──────────────── MAIN MENU ────────────────");
      System.out.println("\nWelcome, " + player.getUsername() + " of " + player.getIslandName() + "!");
      
      System.out.printf("%-8s %-12s %-7s %d [%d / %d XP]\n",
        "Season:", currentSeason,
        "Level:", player.getLevel(), player.getXP(), player.getRequiredXP()
      );

      System.out.printf("%-8s %-12d %-7s $%d\n",
          "Day:", player.getDayCount(),
          "Coins:", player.getCoins()
      );

      System.out.println("\n\"What would you like to do today?\"");
      String topLine    = "┌─────────────────────────┬─────────────────────────┬─────────────────────────┐";
      String separator    = "├─────────────────────────┼─────────────────────────┼─────────────────────────┤";
      String bottomLine = "└─────────────────────────┴─────────────────────────┴─────────────────────────┘";
      System.out.println(topLine);
      System.out.printf("│%-25s│%-25s│%-25s│\n", "       ACTIVITIES", "       MANAGEMENT", "         SYSTEM");
      System.out.println(separator);
      System.out.printf("│ %-23s │ %-23s │ %-23s │\n", "[1] Check Farm Fields", "[4] Open Inventory", "[5] Proceed to Next Day");
      System.out.printf("│ %-23s │ %-23s │ %-23s │\n", "[2] Visit Animal Barn", "", "[6] Leave Game");
      System.out.printf("│ %-23s │ %-23s │ %-23s │\n", "[3] Visit Fishing Dock", "", "");
      System.out.println(bottomLine);

      System.out.print("\n-> ");
      int menuChoice = InputUtils.getValidIntInput(scanner, 1, 6);

      if (menuChoice == 1) {
        checkFarmFields(scanner); 
        continue;
      } else if (menuChoice == 2) {
        visitAnimalBarn(scanner);
      } else if (menuChoice == 3) {
        visitFishingDock(scanner);
        continue;
      } else if (menuChoice == 4) {
        openInventory(scanner);
        continue;
      } else if (menuChoice == 5) {
        proceedToNextDay(scanner);
        continue;
      } else {
        leaveGame(scanner);
        return;
      } 
    }
  }
  // main menu: check farm fields
  public void checkFarmFields(Scanner scanner) {
    while (true) {
      System.out.println("\n──────────────── FARM FIELDS ────────────────");
      cropManager.displayCropSummary();
      System.out.println("\n\"Sun's out, tools out! What mischief-I mean, farming-shall we do today?\"\n");
      System.out.println("[1] Plant New Seeds");
      System.out.println("[2] Water Your Crops");
      System.out.println("[3] Apply Fertilizer");
      System.out.println("[4] Harvest Your Crops");
      System.out.println("[5] I want to do something else");
      System.out.print("\n-> ");

      int farmMenuChoice = InputUtils.getValidIntInput(scanner, 1, 5);
      
      if (farmMenuChoice == 1) {
        cropManager.plantSeeds(currentSeason, player, scanner);
        continue;
      } else if (farmMenuChoice == 2) {
        cropManager.waterCrops(scanner);
        continue;
      } else if(farmMenuChoice == 3) {
        cropManager.fertilizeCrops(scanner);
        continue;
      } else if (farmMenuChoice == 4) {
        cropManager.harvestCrops(scanner);
        continue;
      } else {
        return;
      } 
    }
  }

  // main menu: visit the animals
  public void visitAnimalBarn(Scanner scanner) {
    while (true) {
      System.out.println("\n──────────────── ANIMAL BARN ───────────────\n");
      System.out.println("\"Time to check on your friends!~ What needs doing in the barn today?\"\n");
      System.out.println("[1] Feed Animals");
      System.out.println("[2] Breed Animals");
      System.out.println("[3] Collect Produce");
      System.out.println("[4] They're alright, I'll go do something else");
      System.out.print("\n-> ");

      int barnMenuChoice = InputUtils.getValidIntInput(scanner, 1, 4);

      if (barnMenuChoice == 1) {
        livestockManager.feedAllAnimals(scanner, player);
        continue;
      } else if (barnMenuChoice == 2) {
        livestockManager.breedAnimal(scanner, player);
        continue;
      } else if (barnMenuChoice == 3) {
        livestockManager.collectProduceFromAnimals(scanner, player);
        continue;
      } else {
        return;
      }
    }
  }

  //  main menu: go fish
  public void visitFishingDock(Scanner scanner) {
    while (true) {
      System.out.println("\n──────────────── FISHING DOCK ───────────────\n");
      System.out.println("\"Fishing time! Remember: patience is key, and so is random luck.\"\n");
      System.out.println("[1] Start Fishing");
      System.out.println("[2] I want to do something else");
      System.out.print("\n-> ");
      
      int fishingMenuChoice = InputUtils.getValidIntInput(scanner, 1,2);

      if (fishingMenuChoice == 1) {
        fishManager.catchFish(currentSeason, player, scanner);
        continue;
      } else {
        displayMenu(scanner);
        return;
      } 
    }
  }

  // main menu: open inventory
  public void openInventory(Scanner scanner) {
    while (true) {
      System.out.println("\n───────────────── INVENTORY ─────────────────\n");
      InventoryManager.displayInventoryMenu(scanner);

      System.out.print("-> ");
      int inventoryChoice = InputUtils.getValidIntInput(scanner, 1, 5);

      if(inventoryChoice == 1) {
        InventoryManager.handleCropInventory(scanner, player);
        continue;
      } else if (inventoryChoice == 2) {
        InventoryManager.handleLivestockInventory(scanner, player);
      } else if (inventoryChoice == 3) {
        InventoryManager.handleProduceInventory(scanner, player);
        continue;
      } else if (inventoryChoice == 4) {
        InventoryManager.handleFishInventory(scanner, player);
        continue;
      } else {
        return;
      }
    }
  }

  // main menu: proceed to next day
  public void proceedToNextDay(Scanner scanner) {
    player.nextDay();
    updateSeason();

    System.out.println("\nWelcome to Day " + player.getDayCount() + " of " + currentSeason + "!");
    cropManager.growCrops();
    livestockManager.checkAndGrantLivestock(player);
    livestockManager.growAllLivestock();
    
    InputUtils.waitEnter(scanner);
  }

  // main menu: leave the game
  public void leaveGame(Scanner scanner) {
    System.out.println("\n────────────────── LEAVE GAME ───────────────\n");
    System.out.println("Are you sure you want to quit?");
    System.out.println("[!] Your game will not be saved...");
    System.out.println("\n[1] Yes");
    System.out.println("[2] No");

    while (true) {
      System.out.print("\n-> ");
      int leaveGameChoice = InputUtils.getValidIntInput(scanner, 1, 2);

      if(leaveGameChoice == 1) {
        System.out.println("\n\"Good luck on your future endeavors, " + player.getUsername());
        System.out.println("May the lessons you learned here continue to guide you.\"\n");
        System.exit(0);
      } else {
        InputUtils.waitEnter(scanner);
        displayMenu(scanner);
      }
    }
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

