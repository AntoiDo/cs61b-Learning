public class RotatingSLList<Item> extends SLList<Item> {
    public void rotateRight() {
        Item h = removeLast();
        addFirst(h);
    }
    public static void main(String[] args) {
        RotatingSLList<Integer> hello = new RotatingSLList<>();
        hello.addFirst(1);
        hello.addFirst(2);
        hello.addFirst(3);
        hello.addFirst(4);
        hello.printList();

        hello.rotateRight();
        hello.printList();
    }
}