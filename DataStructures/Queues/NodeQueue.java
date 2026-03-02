package DataStructures.Queues;

public class NodeQueue<E> implements Queue<E> {
    private int size;
    private Node<E> front;
    private Node<E> back;
    
    public NodeQueue() {
        this.size=0;
        this.front=null;
        this.back=null;
    }

    // VERY IMPORTANT FOR MIDTERM
    @Override
    public void enqueue(E name) {
        Node<E> newNod = new Node<E>(name);

        if(size==0) 
        {
            this.front=newNod;
            this.back=newNod;
            size++;
        }
        else 
        {
            this.back.setNext(newNod);
            this.back=newNod;
            size++;
        }
    }

    // VERY IMPORTANT FOR MIDTERM
    @Override
    public E dequeue() {
       if(this.front==null) {
            return null;
       }

       Node<E> temp = this.front;
       this.front=this.front.getNext();
       size--;
       // if front becomes null, then change rear also as
       //null because now both are pointing to same
       if(this.front==null) {
        this.back=null;
       }

       // returning what we have deleted in dequeues operation
       return temp.getValue();
    }

    // VERY IMPORTANT FOR MIDTERM
    @Override
    public int size() {
       return this.size;
    }

    public Node<E> getFirst() {
        return this.front;
    }

    public Node<E> getLast() {
        return this.back;
    }

    public boolean isEmpty() {
        return size==0;
    }

    


    public static void main(String[] args) {
        NodeQueue<String> nq = new NodeQueue();
        System.out.println(nq);
        nq.enqueue("Osama");
        nq.enqueue("Jameel");
        nq.enqueue("Ahmad");
        System.out.println(nq.size());

        Node<String> n= nq.getFirst();
        System.out.println(n);
        while(n.getNext()!=(null)) {
            n=n.getNext();
            System.out.println(n);
        }
        nq.dequeue();
        nq.enqueue("Wasfat");
        nq.enqueue("Ahmad");
        System.out.println(nq);
          
    }
    



    
}
