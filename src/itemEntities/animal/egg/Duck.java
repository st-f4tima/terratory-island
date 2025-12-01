package itemEntities.animal.egg;

import itemEntities.animal.Livestock;
import java.util.Random;

public class Duck extends Livestock{
    private final Random rnd = new Random();
    private static final int EGGS_MIN = 0;
    private static final int EGGS_MAX = 4;
    
    public Duck(String petName, int age){
        super(
            "Duck",
            2400,
            4,
            petName,
            age,
            0,
            false
        ); 
    }

    @Override
    public String getProduce(){
        return "Egg";
    }

    @Override
    protected Livestock birth(String babyName){
        System.out.println("A baby duckling named " + babyName + " has hatched!");
        return new Duck(babyName, 0);
    }

    @Override
    public int collectProduce(){
        if (!this.growthStage.equals("Adult")) {
            System.out.println(this.petName + " is " + this.growthStage.toLowerCase() + " and cannot lay eggs yet.");
            return 0;
        }
        if (!this.isFed) {
            System.out.println(this.petName + " hasn't been fed today and won't produce.");
            return 0;
        }

        int eggsLayed = rnd.nextInt(EGGS_MAX - EGGS_MIN + 1) + EGGS_MIN;
        this.isFed = false;
        if (eggsLayed > 0) {
            System.out.println("\nYou collected " +getProduce() +"/s from " +this.petName +"!");
        } else {
            System.out.println(this.petName + " was fed but didn't lay any eggs today.");
        }
        return eggsLayed;
    }
}
