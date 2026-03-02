package Goats;

public class Attack {
    private String name;
    private int[] hits;
    private DamageType damageType;


    public Attack(String name, int[] hits, DamageType damageType)
    {
        this.name=name;
        this.hits=hits;
        this.damageType=damageType;
    }

     /**
     * Gets Name value
     * @return String name value
     */
    public String getname() {return this.name;}
     /**
     * Gets hits value
     * @return int[] hits value
     */
    public int[] gethits() {return this.hits;}
     /**
     * Gets damageType value
     * @return DamageType damageType value
     */
    public DamageType getdamageType() {return this.damageType;}

    /**
     * sets name to
     * @param name
     */
    public void setname(String name) {this.name=name;}
    /**
     * Sets hits to
     * @param hits
     */
    public void sethits(int[] hits) {this.hits=hits;}
    /**
     * Sets damageType to
     * @param damageType
     */
    public void setdamageType(DamageType damageType) {this.damageType=damageType;}
}