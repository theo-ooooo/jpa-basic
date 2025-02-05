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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("memberA");
            member.setTeam(team);
            em.persist(member);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            member.setTeam(teamB);

            System.out.println("member = " + member.getName());

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
