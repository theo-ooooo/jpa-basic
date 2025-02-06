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
            
            Member member1 = new Member();
            
            member1.setName("hello1");
            
            em.persist(member1);

            Member member2 = new Member();

            member2.setName("hello2");

            em.persist(member2);
            
            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1.getClass() = " + m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());

            System.out.println("reference.getClass() = " + reference.getClass());

            System.out.println("ref" + emf.getPersistenceUnitUtil().isLoaded(reference));
            System.out.println("a==b" + (m1 == reference));

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
