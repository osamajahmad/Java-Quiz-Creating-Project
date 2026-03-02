package DataStructures.Queues;

import java.util.ArrayList;
import java.util.Iterator;

public class Testing {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("My");
        list.add("Name");
        list.add("is");
        list.add("Osama");
        list.add("Jameel");
        list.remove(5);
        list.add("Ahmad");
        list.add(4, "3mk");

        // by index
        for(int i=0; i<list.size(); i++) {
          System.out.println(list.get(i));
        }

        // by index
        for(int i=0; i<list.size(); i++) {
           System.out.println(list.get(i));
        }

        // by element or value
        for(String i:list) {
            System.out.println(i);
        }

        Iterator<String> i = list.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }


    }
}
