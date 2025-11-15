package itemEntities.animal.unique;

import itemEntities.animal.Livestock;

public class Pig extends Livestock{
    
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
    public String collectProduce(){
        //  if not adult
        if (!this.growthStage.equals("Adult")) {
            System.out.println(this.petName + " is " + this.growthStage.toLowerCase() + " and cannot work yet.");
            return null;
        }
        //  feed first
        if (this.isFed) {
            System.out.println("You collected " + getProduce().toLowerCase() + " from " + this.petName + "!");
            this.isFed = false;
            return getProduce();
        } else {
            System.out.println(this.petName + " hasn't been fed today and won't work for produce.");
            return null;
        }
    }
}
