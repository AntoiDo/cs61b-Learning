package DisjointSet;

public class myDisjointSet {
    /**
     * 回顾一下并查集：
     * 这里要实现的是通过Weight Quick Union实现的并查集,下面是思路:
     * 有一个parent[]数组,内容是每个元素的父节点，如果为负数，表示这个节点是根节点，且绝对值是这个集合的大小
     * size[]数组，内容是包括当前节点向下的所有节点的个数
     * 原则：小树往大树上合并
     * 要实现两个函数:          isConnected(int p, int q)  判断两个元素是否在同一个集合
     *                       connect(int p, int q)     将两个元素合并到一个集合
     */
    private int[] parent;
    private int[] size;

    public myDisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;// 初始化每个元素的父节点为-1
            size[i] = 1;
        }
    }

    /**
     * Helper Method.
     * 找到元素p的根节点
     * @param p   元素p
     * @return    根节点(e.g parent[root] < 0, return root)
     */
    private int findRoot(int p) {
        while (parent[p] > 0) {
           p = parent[p];
        }
        return p;
    }

    /**
     * Helper Method. 获得根节点的权重(当前树的节点数)
     * @param root    根节点
     * @return        如果是根节点的话返回权重(Positive)，否则返回0
     */
    private int getWeight(int root) {
        if (parent[root] < 0) {
            return -parent[root];
        } else {
            return 0;
        }
    }
    /**
     * 判断两个元素是否在同一个集合,通过判断根节点是否相同
     * @param p   元素1
     * @param q   元素2
     * @return    是否在同一个集合
     */
    public boolean isConnected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    /**
     * 将两个元素合并到一个集合
     * @param p   元素1
     * @param q   元素2
     */
    public void connect(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) {
            return;
        }
        int weightP = getWeight(rootP); // >0
        int weightQ = getWeight(rootQ); // >0
        if (weightP > weightQ) {
            parent[rootQ] = rootP;
            parent[rootP] -= weightQ;
        } else {
            parent[rootP] = rootQ;
            parent[rootQ] -= weightP;
        }
        // size[]数组的更新
        size[rootP] += size[rootQ];
    }



    public static void main(String[] args) {
        myDisjointSet test = new myDisjointSet(10);
        test.connect(1, 2);
        test.connect(3, 4);
        test.connect(5, 6);
        test.connect(7, 8);
        test.connect(1, 3);
        test.connect(5, 7);
        System.out.println(test.isConnected(1, 5));
        System.out.println(test.isConnected(1,4));
        System.out.println(test.isConnected(1, 8));
    }
}