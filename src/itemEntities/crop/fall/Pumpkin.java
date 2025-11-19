package itemEntities.crop.fall;

import itemEntities.crop.Crop;

public class Pumpkin extends Crop{
  public Pumpkin() {
    super(
      "Pumpkin",
      320,
      24,
      "Fall",
      13,
      false,
      false
    );
  }

  @Override
  public Crop createCopy() {
    return new Pumpkin();
  }
}
