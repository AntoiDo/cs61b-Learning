public class LinkedListDeque<T> implements Deque<T> {
    private static class LinkNode<T> {
        private T data;
        private LinkNode<T> next;
        private LinkNode<T> prev;

        public LinkNode(T item, LinkNode<T> next, LinkNode<T> prev) {
            data = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private LinkNode<T> sentinelFront;
    private LinkNode<T> sentinelRear;
    private int size;

    public LinkedListDeque() {
        sentinelFront = new LinkNode<>(null, null, null);
        sentinelRear = new LinkNode<>(null, null, null);
        sentinelFront.next = sentinelRear;
        sentinelRear.prev = sentinelFront;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        LinkNode<T> newNode = new LinkNode<>(item, sentinelFront.next, sentinelFront);
        sentinelFront.next.prev = newNode;
        sentinelFront.next = newNode;
        size++;
    }

    public void addLast(T item) {
        LinkNode<T> newNode = new LinkNode<>(item, sentinelRear, sentinelRear.prev);
        sentinelRear.prev.next = newNode;
        sentinelRear.prev = newNode;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void printDeque() {
        LinkNode<T> current = sentinelFront.next;
        while (current != sentinelRear) {
            System.out.print(current.data);
            if (current != sentinelRear.prev) {
                System.out.print(" ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = sentinelFront.next.data;
        sentinelFront.next.next.prev = sentinelFront;
        sentinelFront.next = sentinelFront.next.next;
        size--;
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = sentinelRear.prev.data;
        sentinelRear.prev.prev.next = sentinelRear;
        sentinelRear.prev = sentinelRear.prev.prev;
        size--;
        return temp;
    }

    public T get(int index) {
        LinkNode<T> current = sentinelFront.next;
        while (index-- != 0) {
            current = current.next;
        }
        return current.data;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinelFront.next, index);
    }

    private T getRecursiveHelper(LinkNode<T> node, int index) {
        if (index == 0) {
            return node.data;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    // 在节点 p 后插入新节点 newNode
    public void insertAfter(LinkNode<T> p, T newItem) {
        LinkNode<T> newNode = new LinkNode<>(newItem, p.next, p);
        p.next.prev = newNode;
        p.next = newNode;
        size++;
    }

    // 在节点 p 前插入新节点 newNode
    public void insertBefore(LinkNode<T> p, T newItem) {
        LinkNode<T> newNode = new LinkNode<>(newItem, p, p.prev);
        p.prev.next = newNode;
        p.prev = newNode;
        size++;
    }

    // 删除节点 p 的前驱节点
    public void removeBefore(LinkNode<T> p) {
        if (p.prev == sentinelFront) return; // sentinelFront没有前驱节点
        LinkNode<T> toRemove = p.prev;
        toRemove.prev.next = p;
        p.prev = toRemove.prev;
        size--;
    }

    // 删除节点 p 的后继节点
    public void removeAfter(LinkNode<T> p) {
        if (p.next == sentinelRear) return; // sentinelRear没有后继节点
        LinkNode<T> toRemove = p.next;
        toRemove.next.prev = p;
        p.next = toRemove.next;
        size--;
    }

    // 删除节点 p
    public void removeNode(LinkNode<T> p) {
        if (p == sentinelFront || p == sentinelRear) return; // 不删除哨兵节点
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        LinkNode<Integer> ptr = deque.sentinelFront.next.next; // 中间2的那一个

        // 在结点p后面插入结点
        deque.insertAfter(ptr, 99);
        deque.printDeque(); // 1 2 99 3

        // 在结点p前面插入结点
        deque.insertBefore(ptr, 66);
        deque.printDeque(); // 1 66 2 99 3

        // 删除节点p的前驱节点
        deque.removeBefore(ptr);
        deque.printDeque(); // 1 2 99 3

        // 删除节点p的后继节点
        deque.removeAfter(ptr);
        deque.printDeque(); // 1 2 3

        // 删除节点p
        deque.removeNode(ptr);
        deque.printDeque(); // 1 3
    }
}
