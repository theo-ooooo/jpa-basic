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

//            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            em.persist(member);// 1차 캐시.
//
//            em.find(Member.class, 101L); // 1차 캐시에서 들고옴
//
//            System.out.println("findMember.id" + member.getId());
//            System.out.println("findMember.name" + member.getName());

//            Member findMember1 = em.find(Member.class, 101L); // 디비조회후 1차 캐시에 저장
//            Member findMember2 = em.find(Member.class, 101L); // 1차 캐시 조회.
//
//            System.out.println("findMember1 == findMember2 :" + (findMember1 == findMember2));

//            Member member1 = new Member(15323L, "A");
//            Member member2 = new Member(16232L, "B");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("========================");

//            Member member = new Member(201L, "member201");
//
//            em.persist(member);
//
//            em.flush();
//
//            System.out.println("-------------------");

            // 영속상태
            Member member1 = em.find(Member.class, 1L);
            member1.setName("ZZZZZZZ");

            em.close();

            Member member2 = em.find(Member.class, 1L);
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
