public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int front;
    private int rear;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int idx = front;
        for (int i = 0; i < size; i++) {
            System.out.print(array[idx]);
            if (i != size - 1) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
            idx = (idx + 1) % array.length;  // Move to the next index circularly
        }
    }

    private void resize(int newLen) {
        T[] newArray = (T[]) new Object[newLen];
        int idx = front;
        for (int i = 0; i < size; i++) {
            newArray[i] = array[idx];
            idx = (idx + 1) % array.length;
        }
        front = 0;
        rear = size;
        array = newArray;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        front = (front - 1 + array.length) % array.length;  // Circularly move front
        array[front] = item;
        size++;
    }

    public void addLast(T item) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[rear] = item;
        rear = (rear + 1) % array.length;  // Circularly move rear
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = array[front];
        front = (front + 1) % array.length;  // Circularly move front
        size--;
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        rear = (rear - 1 + array.length) % array.length;  // Circularly move rear
        T temp = array[rear];
        size--;
        return temp;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int idx = (front + index) % array.length;
        return array[idx];
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> deque = new ArrayDeque<>();
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addLast(3);
//        deque.addFirst(4);
//        deque.printDeque();
//    }
}
