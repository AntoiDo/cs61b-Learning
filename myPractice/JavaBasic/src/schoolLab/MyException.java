package schoolLab;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyException {
    public static void main(String[] args) {
        try {
            // 1. NullPointerException
            String nullString = null;
            System.out.println(nullString.length());

        } catch (NullPointerException e) {
            System.out.println("捕获空指针异常: " + e.getMessage());
        }

        try {
            // 2. ArrayIndexOutOfBoundsException
            int[] array = new int[5];
            System.out.println(array[10]);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("捕获数组下标越界异常: " + e.getMessage());
        }

        try {
            // 3. StringIndexOutOfBoundsException
            String str = "Hello";
            System.out.println(str.charAt(10));

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("捕获字符串下标越界异常: " + e.getMessage());
        }

        try {
            // 4. ArithmeticException
            int result = 10 / 0;

        } catch (ArithmeticException e) {
            System.out.println("捕获算术异常: " + e.getMessage());
        }

        try {
            // 5. NumberFormatException
            String invalidNumber = "abc";
            int number = Integer.parseInt(invalidNumber);

        } catch (NumberFormatException e) {
            System.out.println("数字格式转换异常: " + e.getMessage());
        }

        try {
            // 6. InputMismatchException
            Scanner scanner = new Scanner("notAnInt");
            System.out.print("Enter an integer: ");
            int input = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("捕获输入不匹配异常: " + e.getMessage());
        }

        try {
            // 7. ClassCastException
            Object i = Integer.valueOf(42);
            String s = (String) i;

        } catch (ClassCastException e) {
            System.out.println("捕获类型强制转换异常: " + e.getMessage());
        }

        System.out.println("End.");
    }
}
