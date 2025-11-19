package itemEntities.crop.summer;

import itemEntities.crop.Crop;

public class Melon extends Crop{
  public Melon() {
    super(
      "Melon",
      250,
      11,
      "Summer",
      12,
      false,
      false
    );
  }

  @Override
  public Crop createCopy() {
    return new Melon();
  }
}
