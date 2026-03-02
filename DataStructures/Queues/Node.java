package DataStructures.Queues;

public class Node <E> {
    private E value;
    private Node<E> next;

    public Node(E name) {
        this.value=name;
        this.next=null;
    }

    public Node(E name, Node<E> nextNode) {
        this.value=name;
        this.next=nextNode;
    }

    public E getValue() {return this.value;}
    public Node<E> getNext() {return this.next;}
    public void setNext(Node<E> nextNode) {this.next=nextNode;}
    public String toString() {
        return "Node: "+this.value;
    }
}
