public class LinkedListDeque<T> {
    /* constructor */
    /* 大概思路:构建双哨兵链表，一个sentinel连接头结点，另外一个指向尾结点，一个节点有prev和next两个指针 */
    private static class LinkNode<T>{
        private T data;
        private LinkNode<T> next;
        private LinkNode<T> prev;

        /**
         * @brief            初始化一个节点
         * @param item      存放的数据
         * @param next      next指向的节点
         * @param prev      prev指向的节点
         */
        public LinkNode(T item, LinkNode<T> next, LinkNode<T> prev){
            data = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private LinkNode<T> sentinelFront;
    private LinkNode<T> sentinelRear;
    private int size;

    /**
     * @介绍    初始化一个双端列表(只建立一个sentinel)
     */
    public LinkedListDeque(){
        sentinelFront = new LinkNode<>(null, null, null);
        sentinelRear = new LinkNode<>(null, null, null);
        sentinelFront.next = sentinelRear;
        sentinelRear.prev = sentinelFront;
        size = 0;  // 初始化大小为0
    }

    /**
     * @breif          在sentinelFront后面添加一个节点
     * @param item     存放的数值
     */
    public void addFirst(T item){
        LinkNode<T> newNode = new LinkNode<>(item, sentinelFront.next, sentinelFront);
        sentinelFront.next.prev = newNode;
        sentinelFront.next = newNode;
        size ++;
    }

    /**
     * @brief          从sentinelRear前面添加一个节点
     * @param item     存放的数值
     */
    public void addLast(T item){
        LinkNode<T> newNode = new LinkNode<>(item, sentinelRear, sentinelRear.prev);
        sentinelRear.prev.next = newNode;
        sentinelRear.prev = newNode;
        size ++;
    }

    /**
     * @brief    队列是否为空
     * @return   (True----isEmpty)  (False----notEmpty)
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * @brief     返回队列大小
     * @return    size
     */
    public int size(){
        return size;
    }

    /**
     * @breif     第一个到最后一个打印双端队列中的项目
     * @return    void
     */
    public void printDeque(){
        LinkNode<T> current = sentinelFront.next;
        do{
            System.out.println(current.data + " ");
            current = current.next;
        }while(current != sentinelRear);
    }

    /**
     * @brief      移除除了sentinel外第一个节点
     * @return     删除节点的item(不存在则返回null)
     */
    public T removeFirst(){
        // 如果找不到就返回null
        if(size == 0){
            return null;
        }
        T temp = sentinelFront.next.data;
        sentinelFront.next.next.prev = sentinelFront;
        sentinelFront.next = sentinelFront.next.next;
        size --;
        return temp;
    }

    /**
     *
     * @return
     */
    public T removeLast(){
        if(size == 0){
            return null;
        }
        T temp = sentinelRear.prev.next.data;
        sentinelRear.prev.prev.next = sentinelRear;
        sentinelRear.prev = sentinelRear.prev.prev;
        size --;
        return temp;
    }

    /**
     * @brief           获得index项的item
     * @param index     [0-Length]
     * @return          index项的item
     */
    public T get(int index){
        /* 从0开始，哨兵first */
        LinkNode<T> current = sentinelFront;
        while(index -- != 0){
            current = current.next;
        }
        return current.data;
    }

    /**
     * @brief         同get
     */
    public T getRecursive(int index){
        if(index > size || index < 0){
            return null;
        }
        return getRecursiveHelper(sentinelFront,index);
    }
    private T getRecursiveHelper(LinkNode<T> node , int index){
        if(index == 0){
            return node.data;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
    public static void main(String[] args){
        LinkedListDeque<String> deque = new LinkedListDeque<String>();
        deque.addFirst("Hello");
        deque.addFirst("World");
        deque.addLast("IDK");
        System.out.println(deque.getRecursive(3));
    }
}
