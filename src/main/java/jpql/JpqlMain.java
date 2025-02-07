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
            jpqlMember.setRole(RoleType.ADMIN);
            em.persist(jpqlMember);


            String query = "select m.username, 'HELLO', TRUE from JpqlMember m where m.role = :role";
            List<Object[]> resultList = em.createQuery(query).setParameter("role", RoleType.ADMIN).getResultList();

            for (Object[] objects : resultList) {
                System.out.println("member = " + objects[0]);
                System.out.println("member = " + objects[1]);
                System.out.println("member = " + objects[2]);
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
