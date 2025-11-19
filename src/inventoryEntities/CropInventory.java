package inventoryEntities;

import java.util.ArrayList;
import java.util.List;
import itemEntities.crop.Crop;

public class CropInventory {
  private List<Crop> plantedCrops;
  private List<Crop> harvestedCrops;

  public CropInventory() {
    this.plantedCrops = new ArrayList<>();
    this.harvestedCrops = new ArrayList<>();
  }

  public void plantCrop(Crop crop) {
    plantedCrops.add(crop);
  }

  public List<Crop> getPlantedCrops() {
    return plantedCrops;
  }
}
