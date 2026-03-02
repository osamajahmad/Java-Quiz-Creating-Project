package Goats;

public abstract class Goat {
    private String name;
    private int maxHP;
    private int currentHP;


    public Goat(String name, int maxHP, int currentHP)
    {
        this.name=name;
        this.maxHP=maxHP;
        this.currentHP=currentHP;

    }


    public String getName() {return name;}
    public int getMaxHP() {return maxHP;}
    public int getCurrentHP() {return currentHP;}


    public void setName(String name) {this.name = name;}
    public void setMaxHP(int maxHP) {this.maxHP = maxHP;}
    public void setCurrentHP(int currentHP) {this.currentHP = currentHP;}

    /**
     * if HP is greater than 0
     * @return True as it is conscious
     */
    public boolean isConscious() {
        return currentHP>0;
    }

    /**
     * Inccreases current HP, heals goat up to the max health allowed
     * @param amount to heal: int
     */
    public void heal(int amount) {
        if(currentHP+amount>maxHP) 
            currentHP=maxHP;
        else
            currentHP+=amount;

    
    }

    @Override
    public String toString() {
        return "Goat: " + name + " (HP: " + currentHP + " out of " + maxHP + ")";
    }

    abstract public Attack attack();

    abstract public void takeDamage(Attack attack);

    
    
}


