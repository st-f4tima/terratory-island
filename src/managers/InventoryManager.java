package managers;

import java.util.Scanner;

public class InventoryManager {
  public static void displayInventoryMenu(Scanner scanner) {
    System.out.println("\"Let's dive into your inventory! Warning: surprises may include absolutely nothing.\"");
    String topLine    = "┌──────────────────────────────┬──────────────────────────────┐";
    String middleLine   = "├──────────────────────────────┼──────────────────────────────┤";
    String bottomLine = "└──────────────────────────────┴──────────────────────────────┘";

    System.out.println("\n" + topLine);
    System.out.printf("│ %-28s │ %-28s │\n", "STORAGE CATEGORIES", "AVAILABLE ACTIONS");
    System.out.println(middleLine);

    System.out.printf("│ %-28s │ %-28s │\n", 
      "[1] View Harvested Crops", 
      "[5] Sell Items"
    );

    System.out.printf("│ %-28s │ %-28s │\n", 
      "[2] View Livestock", 
      "[6] Do something else"
    );

    System.out.printf("│ %-28s │ %-28s │\n", 
      "[3] View Fish", 
      "" 
    );

    System.out.printf("│ %-28s │ %-28s │\n", 
      "[4] View Animal Produce", 
      ""
    );

    System.out.println(bottomLine);
  }
}
