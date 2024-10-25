package test;

import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    private class nameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return d1.name.compareTo(d2.name);
        }
    }


    private String name;
    private int size;

    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Override
    public int compareTo(Dog o) {
        return 0;
    }
}
