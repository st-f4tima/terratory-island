package itemCategory;

import base.Item;

public class Livestock extends Item{ 
    protected String petName;
    protected String growthStage;
    protected int age;

    public Livestock(String name, int cost, int unlockLevel, int xp, String petname, int age){
        super(name, cost, unlockLevel, xp);
        this.petName = petname;
        this.age = age;

        if(age >= 8){
            this.growthStage = "Adult";
        } else if(age >= 4){
            this.growthStage = "Young";
        } else {
            this.growthStage = "Baby";
        }
    }

    // not final
    public void breed(){
        System.out.println("Animals have bred.");
    }

    public void feed(){
        System.out.println("Animals have been fed.");
    }

    // not final
    public int sell() {
        int sellPrice;
        if (this.growthStage.equals("Adult")) {
            sellPrice = (int)(getCost() * 1.0);
            System.out.println("You sold " +this.petName +", a " +getName() +", for " +sellPrice +" coins! Fare well~");
            // sold for full price!
        } else if(this.growthStage.equals("Young")) {
            sellPrice = (int)(getCost() * 0.80);
            System.out.println("You sold " +this.petName +", a " +getName() +", for " +sellPrice +" coins. Good luck out there, child.");
            // a bit young but doable, i guess
        } else if(this.growthStage.equals("Baby")) {
            sellPrice = (int)(getCost() * 0.50);
            System.out.println("You sold " +this.petName +", a " +getName() +", for " +sellPrice +" coins...");
            // why sell the animal so young? :(
        } else {
            System.out.println("You cannot sell something you do not have."); // none in stocc
            return 0;
        } 
        return sellPrice;
    }
}
