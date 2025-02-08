package helloJpa;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.h2.engine.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 요청시 매번 받아야한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            List<Member> resultList = em.createQuery("select m from Member m where m.name like '%kang%'", Member.class).getResultList();


            System.out.println("Criteria");
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "kang"));
            List<Member> resultList1 = em.createQuery(cq).getResultList();
            System.out.println("resultList1 = " + resultList1);


            Member member = new Member();
            member.setName("kang");
            em.persist(member);

            System.out.println("네이티브 SQL 소개");

            String sql = "S";

            List<Member> resultList2 = em.createNativeQuery(sql, Member.class).getResultList();

            for (Member member1 : resultList2) {
                System.out.println("member1 = " + member1.getName());
            }


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
