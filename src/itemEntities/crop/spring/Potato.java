package itemEntities.crop.spring;

import itemEntities.crop.Crop;


public class Potato extends Crop{
  public Potato() {
    super(
      "Potato",
      80,
      1,
      "Spring",
      6, 
      false,
      false
    );
  }

  @Override
  public Crop createCopy() {
    return new Potato();
  }
}

