package BST1;

import java.util.Comparator;

public class FruitComparable implements Comparator<Fruit> {
    

    @Override
    public int compare(Fruit o1, Fruit o2) {
        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
    }

}
