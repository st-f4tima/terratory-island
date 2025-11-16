package itemEntities.animal.unique;

import itemEntities.animal.Livestock;
import java.util.Random;

public class Pig extends Livestock{
    private final Random rnd = new Random();
    private static final int TRUFFLE_MIN = 0;
    private static final int TRUFFLE_MAX = 2;
    
    public Pig(String petName, int age){
        super(
            "Pig",
            32000,
            8,
            petName,
            age,
            0,
            false
        ); 
    }

    @Override
    protected String getProduce(){
        return "Truffle";
    }

    @Override
    protected Livestock birth(String babyName){
        System.out.println("A piglet named " + babyName + " arrived!");
        return new Pig(babyName, 0);
    }

    @Override
    public int collectProduce(){
        if (!this.growthStage.equals("Adult")) {
            System.out.println(this.petName + " is " + this.growthStage.toLowerCase() + " and cannot work yet.");
            return 0;
        }
        if (!this.isFed) {
            System.out.println(this.petName + " hasn't been fed today and won't produce.");
            return 0;
        }

        int truffleFound = rnd.nextInt(TRUFFLE_MAX - TRUFFLE_MIN + 1) + TRUFFLE_MIN;
        this.isFed = false;
        if (truffleFound > 0) {
            System.out.println("You collected " +truffleFound +" " +getProduce() +"(s) from " +this.petName +"!");
        } else {
            System.out.println(this.petName + " was fed but didn't lay any eggs today.");
        }
        return truffleFound;
    }
}
