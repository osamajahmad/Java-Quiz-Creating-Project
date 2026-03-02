package Goats;

public class Mage extends Goat {
    
    private final static int MAX_HEALTH=100;
    public Mage(String name) {
        super(name, MAX_HEALTH, MAX_HEALTH);
    }
    @Override
    public Attack attack() {
        int[] hits = {9,9,9,9};
        Attack strikes = new Attack("Magic Missiles", hits, DamageType.Magical);
        return strikes;
    }
    @Override
    public void takeDamage(Attack attack){
    int hitsTotalDamage=0;
    for(int i=0; i<attack.gethits().length;i++)
    {
        hitsTotalDamage+=attack.gethits()[i];
    }
    if(attack.getdamageType()==DamageType.Magical)
    {
        hitsTotalDamage*=0.75;
    }
    if(attack.getdamageType()==DamageType.Physical)
    {
        hitsTotalDamage*=1.25;
    }
    if(getCurrentHP()>=hitsTotalDamage)
        setCurrentHP(getCurrentHP()-hitsTotalDamage);
    else
        setCurrentHP(0);    

    }
}
