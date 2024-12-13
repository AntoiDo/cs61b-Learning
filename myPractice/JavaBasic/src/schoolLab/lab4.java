package schoolLab;
import java.util.*;

public class lab4 {
    /**
     * experiment 4
     */
    public static void useOfStack() {
        /**
         * Input
         * 对于每组测试数据，第一行是一个正整数 n，0 < n <= 10000(n=0 结束)。而后的 n 行，每行的第一个字符可能是'P’或者'O’或者'A’；
         * 如果是'P’，后面还会跟着一个整数，表示把这个数据压入栈；
         * 如果是'O’，表示将栈顶的值 pop 出来，如果栈中没有元素时，忽略本次操作；
         * 如果是'A’，表示询问当前栈顶的值，如果当时栈为空，则输出'E'。栈开始为空。
         * Output
         * 对于每组测试数据，根据其中的命令字符来处理栈；
         * 并对所有的'A’操作，输出当时栈顶的值，每个占据一行，如果当时栈为空，则输出'E’。当每组测试数据完成后，输出一个空行。
         */
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            Stack stack = new Stack();
            sc.nextLine();// 读取缓冲区的换行符
            while (n-- > 0) {
                String line = sc.nextLine();
                char c = line.charAt(0);
                if (c == 'P') {
                    stack.push(Integer.parseInt(line.substring(2)));
                } else if (c == 'O') {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (c == 'A') {
                    if (stack.isEmpty()) {
                        System.out.println("E");
                    } else {
                        System.out.println(stack.peek());
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * experiment 3
     */
    public static class Student implements Comparable<Student> {
        private String name;
        private long id;

        public Student(String name, long id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public int compareTo(Student o) {
            return Long.compare(this.id, o.id);
        }

        public void print() {
            System.out.println(this.id + " " + this.name);
        }
    }

    public static void sortStudentList() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList();
        while (sc.hasNext()) {
            long id = sc.nextLong();
            String name = sc.nextLine().trim();
            Student s = new Student(name, id);
            list.add(s);
        }
        Collections.sort(list);
        for (Student s : list) {
            s.print();
        }
    }

    /**
     * experiment 2
     */
    public static void reverseString() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            ArrayList<String> list = new ArrayList();
            String s = sc.nextLine();
            // I wanna erase the dot(.) in the end
            if (s.charAt(s.length() - 1) == '.') {
                s = s.substring(0, s.length() - 1);
            }
            String[] words = s.split(" ");
            for (int i = words.length - 1; i >= 0; i--) {
                list.add(words[i]);
            }
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    System.out.println(list.get(i));
                } else {
                    System.out.print(list.get(i) + " ");
                }
            }
        }
    }

    /**
     * experiment 1
     */
    public static void findTheWinner() {
        HashMap<Integer, String> WinningList = new HashMap();
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        while (n-- > 0) {
            int num = sc.nextInt();
            WinningList.put(num, "winwinwin");
        }
        long m = sc.nextInt();
        while (m-- > 0) {
            int num = sc.nextInt();
            if (WinningList.containsKey(num)) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }
    }

    public static void main(String[] args) {
        // useOfStack();
        // sortStudentList();
        // reverseString();
        findTheWinner();
    }
}
