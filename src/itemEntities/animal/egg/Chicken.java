package itemEntities.animal.egg;

import itemEntities.animal.Livestock;
// import java.util.Random;

public class Chicken extends Livestock{

    public Chicken(String petName, int age){
        super(
            "Chicken",
            1600,
            2,
            petName,
            age,
            0,
            false
        ); 
    }

    @Override
    protected String getProduce(){
        return "Egg";
    }

    @Override
    protected Livestock birth(String babyName){
        System.out.println("A baby chick named " + babyName + " has hatched!");
        return new Chicken(babyName, 0);
    }

    @Override
    public String collectProduce(){
        //  if not adult
        if (!this.growthStage.equals("Adult")) {
            System.out.println(this.petName + " is " + this.growthStage.toLowerCase() + " and cannot lay eggs yet.");
            return null;
        }
        //  feed first
        if (this.isFed) {
            System.out.println("You collected an " + getProduce().toLowerCase() + " from " + this.petName + "!");
            this.isFed = false;
            return getProduce();
        } else {
            System.out.println(this.petName + " hasn't been fed today and won't produce.");
            return null;
        }
    }
}
