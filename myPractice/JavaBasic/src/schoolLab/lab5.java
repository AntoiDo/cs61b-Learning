package schoolLab;
import java.util.*;
import java.text.SimpleDateFormat;
public class lab5 {

    public static void findDate() {
        Scanner sc = new Scanner(System.in);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EEEE", Locale.ENGLISH);
        while (sc.hasNext()) {
            long timeOffset = sc.nextInt();
            cal.set(2000,Calendar.JANUARY,1);
            cal.add(Calendar.DATE, (int) timeOffset);
            String formattedDate = sdf.format(cal.getTime());
            System.out.println(formattedDate);
        }
    }

    public static void taskScheduler() {
        Deque<String> taskDeque = new ArrayDeque<>();
        LinkedHashSet<String> taskSet = new LinkedHashSet<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            sc.nextLine();
            while (n-- > 0) {
                String task = sc.nextLine();
                if (task.equals("EXECUTE")) {
                    if (taskDeque.isEmpty()) {
                        System.out.println("EMPTY");
                    } else {
                        System.out.println(taskDeque.pollFirst());
                    }
                } else {
                    String[] taskArr = task.split(" ");
                    String realTask = taskArr[1];
                    if (taskSet.contains(realTask)) {
                        continue;// ignore duplicate task
                    } else {
                        taskDeque.addLast(realTask);
                        taskSet.add(realTask);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        // findDate();
        taskScheduler();
    }
}
