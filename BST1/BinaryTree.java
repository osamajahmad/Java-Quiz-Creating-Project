package BST1;

public class BinaryTree {
    BinaryNode root;

    public void add(int value) {
        root=addRecursive(root,value);
    }

    private BinaryNode addRecursive(BinaryNode current, int value) {
        if(current==null) {
            return new BinaryNode(value);
        }

        if(value<current.value) {
            current.setLeftsubtree(addRecursive(current.getLeftsubtree(), value));
        }

        if(value>current.value) {
            current.setRightsubtree(addRecursive(current.getrightsubtree(), value));
        } else {
            return current;
        }
        return current;
    }

    public void dfsearch() {
        System.out.println("Binary tree Depth First Search: ");
        traverseInOrder(root);
    }

    public void traverseInOrder(BinaryNode node) {
        if (node!=null) {
            traverseInOrder(node.getLeftsubtree()); //Left side
            System.out.print(" " +node.getValue()); //Print middle (Visit)
            traverseInOrder(node.getrightsubtree()); //Right side
        }
    }
}