package itemEntities.animal.milk;

import itemEntities.animal.Livestock;

public class Cow extends Livestock{
    
    public Cow(String petName, int age){
        super(
            "Cow",
            3000,
            4,
            petName,
            age,
            0,
            false
        ); 
    }

    @Override
    protected String getProduce(){
        return "Milk";
    }

    @Override
    protected Livestock birth(String babyName){
        System.out.println("A calf named " + babyName + " arrived!");
        return new Cow(babyName, 0);
    }

    @Override
    public String collectProduce(){
        //  if not adult
        if (!this.growthStage.equals("Adult")) {
            System.out.println(this.petName + " is " + this.growthStage.toLowerCase() + " and cannot be milked yet.");
            return null;
        }
        //  feed first
        if (this.isFed) {
            System.out.println("You collected " + getProduce().toLowerCase() + " from " + this.petName + "!");
            this.isFed = false;
            return getProduce();
        } else {
            System.out.println(this.petName + " hasn't been fed today and won't produce.");
            return null;
        }
    }
}
