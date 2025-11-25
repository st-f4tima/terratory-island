package utils;

import java.util.Scanner;

public class InputUtils {
  public static void waitEnter(Scanner scanner) {
    System.out.println("\nPress ENTER to continue...");
    scanner.nextLine();
  }

  public static int getValidIntInput(Scanner scanner, int max) {
    int choice;
    while (true) {
      System.out.print(" ");
      if(!scanner.hasNextInt()) {
        System.out.println("[Error] Please enter a number.");
        scanner.nextLine(); 
        continue;
      }

      choice = scanner.nextInt();
      scanner.nextLine();

      if(choice < 1 || choice > max) {
        System.out.println("[Error] Choose 1-" + max + ".");
        continue;
        }
      return choice;
      }
  }
}
