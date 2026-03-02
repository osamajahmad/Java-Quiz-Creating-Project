package BST1;

public class Client {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();

        bt.add(10);

        bt.add(4); //left
        bt.add(6); //right

        bt.add(3); //left of 4
        bt.add(5); //right of 4

        bt.add(1); //left of 6
        bt.add(8); //right of 6

        System.out.println();
    }
}
