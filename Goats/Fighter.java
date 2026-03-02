package Goats;

public class Fighter extends Goat {

    private static final int FIGHTER_MAX_HEALTH=150;

    public Fighter(String name) {
        super(name, FIGHTER_MAX_HEALTH, FIGHTER_MAX_HEALTH);
    }
    
    /**
     * gets the hits array damage and name and DamageType
     * @return strikes
     */
    public Attack attack() {
        int[] hits = {25};
        Attack strikes = new Attack("Cleave", hits, DamageType.Physical);
        return strikes;
    }

    /**
     * Applies damage to the current character based on the given attack.
     * Magical increase by 25%, Physical decrease by 25%.
     * If damage exceeds CurrentHP then HP is set to 0
     * @param attack object containing hit values and damage type
     */
    public void takeDamage(Attack attack) {
    int hitsTotalDamage=0;
    for(int i=0; i<attack.gethits().length;i++)
    {
        hitsTotalDamage=hitsTotalDamage+attack.gethits()[i];
    }
    if(attack.getdamageType().equals(DamageType.Magical))
    {
        hitsTotalDamage*=1.25;
    }
    if(attack.getdamageType().equals(DamageType.Physical))
    {
        hitsTotalDamage*=0.75;
    }
    if(getCurrentHP()>=hitsTotalDamage)
        setCurrentHP(getCurrentHP()-hitsTotalDamage);
    else
        setCurrentHP(0);    

    }

    public static void main(String[] args) {
        Fighter mick = new Fighter("Mickey");
        Fighter nick = new Fighter("Nicolas");

        Mage mark = new Mage("Marco");

        System.out.println(mick);
        System.out.println(nick);
        System.out.println(mark);

        Attack a = mark.attack();
        nick.takeDamage(a);

        mark.takeDamage(mick.attack());
        mick.takeDamage(mark.attack());

        System.out.println(mick);
        System.out.println(nick);
        System.out.println(mark);

        combatArena(mark,mick);

        Goat jacke = new Fighter("Konan");
        Goat fin = new Mage("Arnold");
        combatArena(jacke,fin);
    }

   

    public static void combatArena(Goat m, Goat f) {
        while (m.isConscious() && f.isConscious()) {
            m.takeDamage(f.attack());
            f.takeDamage(m.attack());
        }
        if(m.isConscious()) {
            System.out.println("Winner is "+m.getName()+"!");
        }
        else if(f.isConscious()) {
            System.out.println("Winner is "+f.isConscious()+"!");
        }
        else 
            System.out.println("No one Won!");

    }

    
}

