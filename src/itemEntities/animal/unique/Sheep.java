package itemEntities.animal.unique;

import itemEntities.animal.Livestock;

public class Sheep extends Livestock{
    
    public Sheep(String petName, int age){
        super(
            "Sheep",
            12000,
            6,
            petName,
            age,
            0,
            false
        ); 
    }

    @Override
    public String getProduce(){
        return "Wool";
    }

    @Override
    protected Livestock birth(String babyName){
        System.out.println("A lamb named " + babyName + " arrived!");
        return new Sheep(babyName, 0);
    }

    @Override
    public int collectProduce(){
        //  if not adult
        if (!this.growthStage.equals("Adult")) {
            System.out.println(this.petName + " is " + this.growthStage.toLowerCase() + " and cannot be sheared yet.");
            return 0;
        }
        //  feed first
        if (this.isFed) {
            System.out.println("\nYou collected " + getProduce().toLowerCase() + " from " + this.petName + "!");
            this.isFed = false;
            return 1;
        } else {
            System.out.println(this.petName + " hasn't been fed today and refuses to be sheared.");
            return 0;
        }
    }
}