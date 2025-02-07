package helloJpa;

import org.h2.engine.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 요청시 매번 받아야한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            
            Address address = new Address("city", "street", "zipcode");
            
            Member member = new Member();
            member.setName("A");
            member.setHomeAddress(address);
            em.persist(member);

            Address newCity = new Address("newCity", address.getStreet(), address.getZipcode());

            member.setHomeAddress(newCity);

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
