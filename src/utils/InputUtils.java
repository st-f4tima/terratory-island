package utils;

import java.util.Scanner;

public class InputUtils {
  public static void waitEnter(Scanner scanner) {
    System.out.println("\nPress ENTER to continue...");
    scanner.nextLine();
  }

  public static int getValidIntInput(Scanner scanner, int min, int max) {
    while (true) {
      try {
          String input = scanner.nextLine().trim();

          // validate: must be a number (used regular expresion here)
          if (!input.matches("\\d+")) {
            showError(scanner, min, max);
            continue;
          }

          int value = Integer.parseInt(input);

          // validate: if must be within the given range
          if (value < min || value > max) {
            showError(scanner, min, max);
            continue;
          }

          return value; 
      } catch (Exception e) {
        showError(scanner, min, max);
      }
    }
  }

// reusable error message method 
  private static void showError(Scanner scanner, int min, int max) {
    System.out.println("[Error] Please enter a number (" + min + "-" + max + ").");
    System.out.println("Press ENTER to try again...");
    scanner.nextLine();
    System.out.print("-> ");
  }
}
