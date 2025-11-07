package itemCategory;

import base.Item;

public class Fish extends Item {
    protected String season;
    protected String rarity;
    protected boolean isCatched;

    public Fish (String name, int cost, int unlockLevel, int xp, String season, String rarity){
        super (name, cost, unlockLevel, xp);
        this.isCatched = false;
    }

    public void catchFish (){
        // randomizer
        System.out.println("You catched a ");
    }

    public int sell () {
        if (isCatched) {
            int sellPrice = (int)(getCost() * 3);
            System.err.println("You sold a " + getName() + " for " + sellPrice + " coins!");
            return sellPrice;
        } else {
            System.out.println("You haven't caught any fish yet.");
            return 0;
        } 
    }

}

