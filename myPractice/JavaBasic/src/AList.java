public class AList {
    int size;
    int[] item;

    public AList() {
        this.size = 0;
        item = new int[100];
    }
    /* Insert the X into the end of list */
    public void addLast(int x){
        if(size == item.length){
            int[] temp = new int[item.length + 1];
            System.arraycopy(item, 0, temp, 0, size);
            item = temp;
        }
        item[size] = x;
        size ++;
    }

}
