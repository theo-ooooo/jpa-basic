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

            List<MemberDTO> resultList = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from JpqlMember m", MemberDTO.class).getResultList();

            MemberDTO memberDTO = resultList.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());


        }catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }
}
