package managers;

import java.util.List;
import itemEntities.crop.Crop;
import itemEntities.crop.fall.Grape;
import itemEntities.crop.fall.Pumpkin;
import itemEntities.crop.fall.SweetGemBerry;
import itemEntities.crop.spring.Cauliflower;
import itemEntities.crop.spring.Potato;
import itemEntities.crop.spring.Strawberry;
import itemEntities.crop.summer.Melon;
import itemEntities.crop.summer.Starfruit;
import itemEntities.crop.summer.Wheat;

public class CropManager {
  List<Crop> springCrops;
  List<Crop> summerCrops;
  List<Crop> fallCrops;

  public CropManager() {
    springCrops = List.of(new Potato(), new Strawberry(), new Cauliflower());
    summerCrops = List.of(new Wheat(), new Melon(), new Starfruit());
    fallCrops = List.of(new Grape(), new Pumpkin(), new SweetGemBerry());
  }

  public void displayAvailableCrops(String currentSeason ) {
    System.out.println("\n────────────── AVAILABLE CROPS ──────────────\n");
    System.out.println("Seasong: " + currentSeason);

    List<Crop> availableCrops;

    switch (currentSeason) {
      case "Spring":
        availableCrops = springCrops;
        break;
      case "Summer": 
        availableCrops = summerCrops;
        break;
      case "Fall":
        availableCrops = fallCrops;
        break;
      case "Winter":
        System.out.println("No crops can be planted in Winter.");
        return;
      default:
        System.out.println("Invalid season.");
        return;
    }

    for(int i = 0; i < availableCrops.size(); i++) {
      Crop crop = availableCrops.get(i);
      int requiredLevel = crop.getUnlockLevel();
      System.out.printf("\n%d. %s (Level required: %d)", (i + 1), crop.getName(), requiredLevel);
    }
  }
}