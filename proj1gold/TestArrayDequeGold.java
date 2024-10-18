import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentDequeArray() {
        StudentArrayDeque<Integer> testDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correctDeque = new ArrayDequeSolution<>();
        String log = "";
        for(int i = 0; i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                testDeque.addLast(i);
                correctDeque.addLast(i);
                log = log + "addLast(" + i + ")\n";
            } else {
                testDeque.addFirst(i);
                correctDeque.addFirst(i);
                log = log + "addFirst(" + i + ")\n";
            }
        }
        // test
        Integer expected, actual;
        for(int i = 0; i < 10; i++) {
            int numberBetweenZeroAndOne = StdRandom.uniform(2);
            switch(numberBetweenZeroAndOne) {
                case 0:
                    actual = testDeque.removeFirst();
                    expected = correctDeque.removeFirst();
                    log = log + "removeFirst()\n";
                    assertEquals(log, expected, actual);
                    break;
                case 1:
                    actual = testDeque.removeLast();
                    expected = correctDeque.removeLast();
                    log = log + "removeLast()\n";
                    assertEquals(log, expected, actual);
                    break;
            }
        }
        System.out.println("Pass the test!");
    }
}
