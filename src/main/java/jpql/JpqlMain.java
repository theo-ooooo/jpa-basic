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
            JpqlTeam jpqlTeam = new JpqlTeam();
            jpqlTeam.setName("B");
            em.persist(jpqlTeam);

            JpqlMember jpqlMember = new JpqlMember();
            jpqlMember.setUsername("team");
            jpqlMember.setAge(10);
            jpqlMember.changeTeam(jpqlTeam);
            em.persist(jpqlMember);


            String query = "select m from JpqlMember m  join m.team t on t.name = 'A'";
            List<JpqlMember> resultList = em.createQuery(query, JpqlMember.class).getResultList();

            for (JpqlMember member : resultList) {
                System.out.println("member = " + member);
            }

            tx.commit();


        }catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }
}
