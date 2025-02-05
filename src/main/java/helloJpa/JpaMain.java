package helloJpa;

import org.h2.engine.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 요청시 매번 받아야한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = new Member();
//            member.setId("ID_A");
            member.setUsername("C");

            Member member1 = new Member();
            member1.setUsername("D");

            Member member2 = new Member();
            member2.setUsername("E");

            em.persist(member1);
            em.persist(member);
            em.persist(member2);
//
            tx.commit();
        }catch(Exception e) {
            System.out.println("e" + e.getMessage());
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
