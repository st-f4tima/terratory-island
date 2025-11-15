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
        updateGrowthStage();
    }

    //  progress
    protected void updateGrowthStage(){
        if(age >= 8){
            this.growthStage = "Adult";
            if(this.breedCooldown == 0){
                this.breedCooldown = 7;
            }
        } else if(age >= 4){
            this.growthStage = "Young";
            this.breedCooldown = 0;
        } else {
            this.growthStage = "Baby";
            this.breedCooldown = 0;
        }
    }

    public void passDay(){
        this.age++;
        if (this.breedCooldown > 0) {
            this.breedCooldown--;
        }
        updateGrowthStage();
        this.isFed = false;
    }

    //  methods: animal-specific 
    protected abstract String getProduce();
    protected abstract Livestock birth();

    //  methods: called in play
    public final Livestock breed(){
        // breed condition: too young
        if(this.growthStage.equals("Young")){
            System.out.println("This one is too young to breed.");
            return null;
        // breed condition: just a baby
        } else if(this.growthStage.equals("Baby")){
            System.out.println("No. It's just a baby...");
            return null;
        // breed condition: adult
        } else {
            System.out.println(this.petName + " has achieved gestation.");
            Livestock baby = this.birth();
            return baby;
        }
    }

    public void feed(){
        if(!isFed){
            this.isFed = true;
            System.out.println(this.petName + " has been fed.");
        } else {
            System.out.println(this.petName + " already ate.");
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
            // a bit young but do-able, i guess
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
