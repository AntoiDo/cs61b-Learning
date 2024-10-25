package schoolLab;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class lab3 {
    public static void aPlusBVersionStr() {
        // give me some hints about how to complete A+B problem in string version, requiring delete illegal characters
        // and convert string to integer
        // I will give you some hints
        // 1. You can use the function of String.charAt(int index) to get the character at the index of the string
        // 2. You can use the function of Character.isDigit(char ch) to check whether the character is a digit
        // 3. You can use the function of Integer.parseInt(String s) to convert the string to integer
        // 4. You can use the function of StringBuilder.append(char ch) to append a character to the end of the string

        // todo: implement the function
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String aline = sc.nextLine();
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            // how to access each char in aline
            for (int i = 0; i < aline.length(); i++) {
                char a = aline.charAt(i);
                if (Character.isDigit(a)) {
                    sb.append(a);
                } else if (a == ' ' || a == '\n') {
                    sum += Integer.parseInt(sb.toString());
                }
            }
            System.out.println(sum);
        }
    }

    /* Input
    输入第一行为一个数字n (1<n<10000)，表示接下来一共有n个备选数字，int范围，以回车分隔，且不会重复。
    然后接下来是为一个数字m  (1<m<5000)，表示接下来有m个同学所选的数字，int范围内，以回车分隔，你需要输出的是这个同学的数字是否在上述的备选数字中。 */
    public static void findTheWinner() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            // 输入同学所选的数字
            int findFlag = 0;
            int figure = sc.nextInt();

            for (int j = 0; j < n; j++) {
                if (figure == nums[j]) {
                    System.out.println("true");
                    findFlag = 1;
                    break;
                }
            }
            if (findFlag == 0)
                System.out.println("false");
        }
    }

    public static void substringOfWebsiteURL() {
        // how to find a substring
        // 1. You can use the function of String.indexOf(String str) to find the index of the first occurrence of the substring
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int beginIndex = 0;
            int endIndex = 0;
            String aline = sc.nextLine();
            beginIndex = aline.indexOf("://");
            if (beginIndex != -1) {
                beginIndex += 3;
                endIndex = aline.indexOf("/", beginIndex);
                if (endIndex == -1) {
                    endIndex = aline.length();
                }
                System.out.println(aline.substring(beginIndex, endIndex));
            }
        }
    }

    /* Input
    多组数据输入，每组输入数据一共四行,数据均在int范围内。 第一行2个整数是n,m，分别表示这门课的学生总数和今天这门课的学生到的人数。
    接下来一行有n个不同的数a[i](1<=i<=n)，表示学生的编号。
    下面一行m个不同的数b[i](1<=i<=m)，表示到课学生的编号。最后一行一个数k表示土豪S的编号. */
    public static void findTuhao() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] a = new int[n];
            Set<Integer> presentStudents = new HashSet<>();

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                presentStudents.add(sc.nextInt());
            }
            int k = sc.nextInt();

            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!presentStudents.contains(a[i])) {
                    if (a[i] == k) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("NO");
            }
        }
        sc.close();
    }

    /* Description
    Jack likes to calculate A+B, but when he enters A and B with a computer, he accidentally mixes some illegal symbols with the numbers, and finally brings difficulties to the calculation. Now, Jack need to use Java for help.
    Input
    Jack likes to calculate A+B, but when he enters A and B with a computer, he accidentally mixes some illegal symbols with the numbers, and finally brings difficulties to the calculation. Now, Jack need to use Java for help.
    Output
    After removing illegal symbols, you need to print the sum of A and B */
    public static void calculateAB() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String aline = sc.nextLine();
            int sum = 0;
            StringBuilder sb = new StringBuilder();
            boolean isNegative = false;
            for (int i = 0; i < aline.length(); i++) {
                char a = aline.charAt(i);
                if (a == '-') {
                    isNegative = true;
                } else if (Character.isDigit(a)) {
                    sb.append(a);
                } else if (a == ' ' || a == '\n') {
                    if (sb.length() > 0) {
                        int number = Integer.parseInt(sb.toString());
                        if (isNegative) {
                            number = -number;
                        }
                        sum += number;
                        sb.setLength(0); // Reset the StringBuilder
                        isNegative = false;
                    }
                }
            }
            if (sb.length() > 0) {
                int number = Integer.parseInt(sb.toString());
                if (isNegative) {
                    number = -number;
                }
                sum += number;
            }
            System.out.println(sum);
        }
    }

    /* Description
    给定一个网址字符串，你的任务是提取出网站的主域名。你需要考虑以下情况：
    网址可能使用各种协议，包括但不限于：http,https,ftp,ftps,file,ws,wss。
    忽略子域名，只提取主域名。例如，对于blog.example.com，只提取example.com。
    网址可能包含非标准端口，如http://example.com:8080/，但你应该忽略这些端口。
    请确保对大小写不敏感，即HTTP://EXAMPLE.COM/和http://example.com/应当被视为相同，并输出为example.com。
    Input
    输入为一行行的网址字符串，保证每个网址的长度不超过1000个字符。
    Output
    输出每个网址的主域名地址，全部小写。 */
    public static void websiteURLAdvanced() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String url = sc.nextLine().toLowerCase();
            // 先提取出主页信息的字符串
            int beginIndex = url.indexOf("://");
            if (beginIndex != -1) {
                beginIndex += 3;
                int endIndex = url.indexOf("/", beginIndex);
                if (endIndex == -1) {
                    endIndex = url.length();
                }
                String mainURL = url.substring(beginIndex, endIndex);
                // 再提取出主域名
                int mainBeginIndex = mainURL.indexOf(".");
                if (mainBeginIndex != -1) {
                    mainBeginIndex += 1;
                    // ftp://files.EXAMPLE.NET:2121/download.zip 对于这种情况，需要考虑端口号
                    int portIndex = mainURL.indexOf(":");
                    if (portIndex != -1) {
                        mainURL = mainURL.substring(0, portIndex);
                        mainURL = mainURL.substring(mainBeginIndex);
                        System.out.println(mainURL);
                        continue;
                    }
                    mainURL = mainURL.substring(mainBeginIndex);
                    int mainEndIndex = mainURL.indexOf(".");
                    mainURL = mainURL.substring(mainEndIndex + 1);
                    System.out.println(mainURL);
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        // findTheWinner();
        // substringOfWebsiteURL();
        // findTuhao();
        // calculateAB();
        websiteURLAdvanced();
    }
}