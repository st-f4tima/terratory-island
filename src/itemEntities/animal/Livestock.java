package itemEntities.animal;

import base.Item;

public abstract class Livestock extends Item{ 
    protected String petName;
    protected String growthStage;
    protected int age;
    protected int breedCooldown;
    protected boolean isFed;

    public Livestock(String name, int cost, int unlockLevel, String petName, int age, int breedCooldown, boolean isFed){
        super(name, cost, unlockLevel);
        this.petName = petName;
        this.age = age;
        this.breedCooldown = breedCooldown;
        this.isFed = isFed;

        if(age >= 8){
            this.growthStage = "Adult";
            this.breedCooldown = 7; // days
        } else if(age >= 4){
            this.growthStage = "Young";
        } else {
            this.growthStage = "Baby";
        }
    }

    protected abstract Livestock birth();

    public final Livestock breed(){
        // breed conditions:
        if(this.growthStage.equals("Young")){
            System.out.println("This one is too young to breed.");
        } else if(this.growthStage.equals("Baby")){
            System.out.println("No. It's just a baby...");
        } else {
            System.out.println("Gestation achieved.");
        }
        Livestock baby = this.birth();
        return baby;
    }

    public void feed(){
        if(!isFed){
            this.isFed = true;
            System.out.println("This one has been fed.");
        } else {
            System.out.println("This one already ate.");
        }
        
    }

    @Override
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
