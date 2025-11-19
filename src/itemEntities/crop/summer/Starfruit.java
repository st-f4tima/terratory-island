package itemEntities.crop.summer;

import itemEntities.crop.Crop;

public class Starfruit extends Crop{
  public Starfruit() {
    super(
      "Starfruit",
      750,
      14,
      "Summer",
      13,
      false, 
      false
    );
  }

  @Override
  public Crop createCopy() {
    return new Starfruit();
  }
}
