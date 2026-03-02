package BST1;

public class Fruit implements Comparable<Fruit> {
    private String name;
    private int price;

    
    public Fruit(String name, int price) {
        this.name = name;
        this.price = price;
    }


    public String getName() {return name;}
    public void setName(String name) {this.name = name;}


    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}


    @Override
    public int compareTo(Fruit other) {
        return this.getPrice()-other.getPrice();
    }

    public static void main(String[] args) {
        Fruit Bananna = new Fruit("MOOZ", 6);
        Fruit apple = new Fruit("Apple", 3);

        System.out.println(apple.compareTo(Bananna));
     
        
        FruitComparable fc= new FruitComparable();
        System.out.println(fc.compare(Bananna,apple));
    }
    

}
