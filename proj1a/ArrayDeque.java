//todo:将arrayDeque改造成循环数组(using %)
public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int front;
    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        front = 0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void printDeque() {
        for(int i = front; i < size; i++){
            System.out.print(array[i]);
            if(i != size - 1){
                System.out.print(" ");
            }else{
                System.out.println();
            }
        }
    }
    private void resize(int newLen) {
        T[] newArray = (T[])new Object[newLen];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
    public void addFirst(T item) {
        if(size == array.length){
            resize(size * 2);
        }
        for(int i = size; i > 0; i--){
            array[i] = array[i-1];
        }
        array[0] = item;
        size ++;
    }
    public void addLast(T item){
        if(size == array.length){
            resize(size * 2);
        }
        array[size++] = item;
    }
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T temp = array[0];
        for(int i = 1; i < size; i++){
            array[i-1] = array[i];
        }
        size --;
        return temp;
    }
    public T removeLast(){
        if(size == 0){
            return null;
        }
        T temp = array[size - 1];
        size -- ;
        return temp;
    }
    public T get(int index){
        return array[index];
    }


//    public static void main(String[] args){
//        ArrayDeque<Integer> deque = new ArrayDeque<>();
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        deque.addLast(6);
//        deque.removeLast();
//        deque.printDeque();
//    }
}