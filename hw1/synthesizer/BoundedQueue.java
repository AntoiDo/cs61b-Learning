package synthesizer;
import java.util.Iterator;
public interface BoundedQueue <T> extends Iterable<T> {
    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return (but do not delete) item from the front

    /**
     * Returns true if the buffer is empty, false otherwise
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * Returns true if the buffer is full, false otherwise
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }

    @Override
    Iterator<T> iterator();
}
