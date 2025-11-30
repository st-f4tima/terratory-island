package managers;

import java.util.Scanner;
import utils.InputUtils;
import base.Player;

public class InventoryManager {
  public static void displayInventoryMenu(Scanner scanner) {
    System.out.println("\"Let's dive into your inventory! Warning: surprises may include absolutely nothing.\"");
    System.out.println("\n[1] View Harvested Crops");
    System.out.println("[2] View Livestock");
    System.out.println("[3] View Animal Produce");
    System.out.println("[4] View Fish");
    System.out.println("[5] I want to do something else\n");
  }

  public static void displaySellChoice() {
    System.out.println("\n\"Time to cash in your hard work!\"");
    System.out.println("\n[1] Sell an item");
    System.out.println("[2] I want do do something else\n");
  }

  public static void handleCropInventory(Scanner scanner, Player player) {
    while (true) {
      player.getCropInventory().viewData();
      InventoryManager.displaySellChoice();
      System.out.print("-> ");
      
      int sellChoice = InputUtils.getValidIntInput(scanner, 1, 2);

      if(sellChoice == 1) {
        player.getCropInventory().SellOneCrop(player, scanner);
        InputUtils.waitEnter(scanner);
        break;
      } else {
        InputUtils.waitEnter(scanner);
        break;
      }
    }
  }

  public static void handleFishInventory(Scanner scanner, Player player) {
    while(true) {
      player.getFishInventory().viewData();
      InventoryManager.displaySellChoice();
      System.out.print("-> ");

      int sellChoice = InputUtils.getValidIntInput(scanner, 1, 2);

      if(sellChoice == 1){
        player.getFishInventory().SellOneFish(player,scanner);
        InputUtils.waitEnter(scanner);
        break;
      } else {
        InputUtils.waitEnter(scanner);
        break;
      }
    }
  }

  public static void handleLivestockInventory(Scanner scanner, Player player) {
    while (true) {
      player.getLivestockInventory().viewData();
      InventoryManager.displaySellChoice();
      System.out.print("-> ");
      
      int sellChoice = InputUtils.getValidIntInput(scanner, 1, 2);

      if(sellChoice == 1) {
        player.getLivestockInventory().sellOneLivestock(scanner, player);
        InputUtils.waitEnter(scanner);
        break;
      } else {
        InputUtils.waitEnter(scanner);
        break;
      }
    }
  }

  public static void handleProduceInventory(Scanner scanner, Player player) {
    while (true) {
      player.getProduceInventory().viewData();
      InventoryManager.displaySellChoice();
      System.out.print("-> ");
      
      int sellChoice = InputUtils.getValidIntInput(scanner, 1, 2);

      if(sellChoice == 1){
        player.getFishInventory().SellOneFish(player, scanner);
        InputUtils.waitEnter(scanner);
        break;
      } else {
        InputUtils.waitEnter(scanner);
        break;
      }
    }
  }
}

