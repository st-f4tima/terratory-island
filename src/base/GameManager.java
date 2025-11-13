package base;

public class GameManager {
  private Player player;
  private String currentSeason;

  public GameManager(Player player) {
    this.player = player;
    this.currentSeason = "Spring";
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

