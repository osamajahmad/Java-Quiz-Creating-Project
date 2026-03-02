package BST1;

public class BinaryNode {
    
    private BinaryNode leftSubtree;
    private BinaryNode rightSubtree;
    int value;

    // Parametrized constructor, taking 1
    public BinaryNode(int value) {
        this.value=value;
        rightSubtree = leftSubtree = null;
        //leftSubtree = null;
    }


    public BinaryNode getLeftsubtree() {
        return leftSubtree;
    }

    public BinaryNode getrightsubtree() {
        return rightSubtree;
    }

    public void setLeftsubtree(BinaryNode leftSubtree) {
        this.leftSubtree=leftSubtree;
    }

    public void setRightsubtree(BinaryNode rightSubtree) {
        this.rightSubtree=rightSubtree;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value=value;
    }

    public String toString() {
        return "BinaryNode{value=<"+value+">, left=<" + leftSubtree + ">, right=<" + rightSubtree + ">}";
    }


    public static void main(String[] args) {
        
    }

}
