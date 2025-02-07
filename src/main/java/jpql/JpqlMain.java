package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpqlMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 요청시 매번 받아야한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            JpqlMember jpqlMember = new JpqlMember();
            jpqlMember.setUsername("member1");
            jpqlMember.setAge(10);
            em.persist(jpqlMember);

            JpqlMember singleResult = em.createQuery("select m from JpqlMember m where m.username = :username", JpqlMember.class)
                    .setParameter("username", "member1").getSingleResult();

            System.out.println("singleResult.getId() = " + singleResult.getId());

//            for (JpqlMember member : singleResult) {
//                System.out.println("member = " + member);
//            }

        }catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }
}
