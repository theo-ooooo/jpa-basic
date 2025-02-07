package helloJpa;

import org.h2.engine.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 요청시 매번 받아야한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            
            Member member = new Member();
            member.setName("A");
            member.setHomeAddress(new Address("homeCity", "street", "zipcode1"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("햄버거");

            member.getAddressHistory().add(new AddressEntity("old1", "street2", "zipcode2"));
            member.getAddressHistory().add(new AddressEntity("old2", "street2", "zipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("__________START");
            Member findMember = em.find(Member.class, member.getId());

            Address oldAddress = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", oldAddress.getStreet(), oldAddress.getZipcode()));

            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new AddressEntity("old2", "street2", "zipcode2"));
            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street2", "zipcode2"));
            tx.commit();
        }catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }


}
