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

        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");

        System.out.println("==" + (address1 == address2));
        System.out.println("equals" + address1.equals(address2));
    }
}
