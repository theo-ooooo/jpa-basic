package helloJpa;

public class ValueMain {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(10);
        Integer b = a;
        a = 20;

        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
