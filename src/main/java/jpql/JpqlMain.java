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
            for (int i = 0; i < 100; i++) {
                JpqlMember jpqlMember = new JpqlMember();
                jpqlMember.setUsername("member" + i);
                jpqlMember.setAge(i);
                em.persist(jpqlMember);
            }


            List<JpqlMember> resultList = em.createQuery("select m from JpqlMember m order by m.age desc", JpqlMember.class).setFirstResult(0).setMaxResults(10).getResultList();

            System.out.println("result.size " + resultList.size());
            for (JpqlMember member : resultList) {
                System.out.println("member = " + member);
            }


        }catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }
}
