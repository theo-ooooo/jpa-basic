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
            JpqlTeam teamA = new JpqlTeam();
            teamA.setName("teamA");
            em.persist(teamA);

            JpqlTeam teamB = new JpqlTeam();
            teamB.setName("teamB");
            em.persist(teamB);

            JpqlTeam teamC = new JpqlTeam();
            teamC.setName("teamC");
            em.persist(teamC);

            JpqlMember jpqlMember = new JpqlMember();
            jpqlMember.setUsername("회원1");
            jpqlMember.setAge(10);
            jpqlMember.changeTeam(teamA);
            jpqlMember.setRole(RoleType.ADMIN);
            em.persist(jpqlMember);

            JpqlMember jpqlMember2 = new JpqlMember();
            jpqlMember2.setUsername("회원2");
            jpqlMember2.setAge(10);
            jpqlMember2.changeTeam(teamA);
            jpqlMember2.setRole(RoleType.ADMIN);
            em.persist(jpqlMember2);

            JpqlMember jpqlMember3 = new JpqlMember();
            jpqlMember3.setUsername("회원3");
            jpqlMember3.setAge(10);
            jpqlMember3.changeTeam(teamB);
            jpqlMember3.setRole(RoleType.ADMIN);
            em.persist(jpqlMember3);

            JpqlMember jpqlMember4 = new JpqlMember();
            jpqlMember4.setUsername("회원4");
            jpqlMember4.setAge(10);
            jpqlMember4.changeTeam(teamC);
            jpqlMember4.setRole(RoleType.ADMIN);
            em.persist(jpqlMember4);

            int resultCount = em.createQuery("update JpqlMember m set m.age = 20").executeUpdate();
            System.out.println("resultCount = " + resultCount);

            em.clear();

            JpqlMember findMember = em.find(JpqlMember.class, jpqlMember.getId());

            System.out.println("member.age" + findMember.getAge());
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
