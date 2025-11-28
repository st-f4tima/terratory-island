package itemEntities.animal.milk;

import itemEntities.animal.Livestock;

public class Goat extends Livestock{
    
    public Goat(String petName, int age){
        super(
            "Goat",
            8000,
            6,
            petName,
            age,
            0,
            false
        ); 
    }

    @Override
    public String getProduce(){
        return "Milk";
    }

    @Override
    protected Livestock birth(String babyName){
        System.out.println("A kid named " + babyName + " arrived!");
        return new Goat(babyName, 0);
    }

    @Override
    public int collectProduce(){
        //  if not adult
        if (!this.growthStage.equals("Adult")) {
            System.out.println(this.petName + " is " + this.growthStage.toLowerCase() + " and cannot be milked yet.");
            return 0;
        }
        //  feed first
        if (this.isFed) {
            System.out.println("You collected " + getProduce().toLowerCase() + " from " + this.petName + "!");
            this.isFed = false;
            return 1;
        } else {
            System.out.println(this.petName + " hasn't been fed today and won't produce.");
            return 0;
        }
    }
}
