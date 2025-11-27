package utils;

import java.util.Scanner;

public class InputUtils {
  public static void waitEnter(Scanner scanner) {
    System.out.println("\nPress ENTER to continue...");
    scanner.nextLine();
  }

  public static int getValidIntInput(Scanner scanner, int min, int max) {
    while (true) {
      String input = scanner.nextLine().trim();

      if (!input.matches("\\d+")) {
          System.out.println("[Error] Please enter a number (" + min + "-" + max + ").");
          System.out.println("Press ENTER to try again...");
          scanner.nextLine();
          System.out.print("-> ");
          continue;
      }

      int value = Integer.parseInt(input);
      
      if (value < min || value > max) {
          System.out.println("[Error] Please choose between " + min + " and " + max + ".");
          System.out.println("Press ENTER to try again...");
          scanner.nextLine();
          System.out.print("-> ");
          continue;
      }

      return value; 
    }
  }
}
