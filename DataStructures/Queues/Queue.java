package DataStructures.Queues;

public interface Queue <E>{
    
    void enqueue(E name);
    E dequeue();
    int size();
}
