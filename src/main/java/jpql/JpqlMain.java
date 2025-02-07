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
            jpqlTeam.setName("관리자");
            em.persist(jpqlTeam);

            JpqlMember jpqlMember = new JpqlMember();
            jpqlMember.setUsername("관리자1");
            jpqlMember.setAge(10);
            jpqlMember.changeTeam(jpqlTeam);
            jpqlMember.setRole(RoleType.ADMIN);
            em.persist(jpqlMember);

            JpqlMember jpqlMember2 = new JpqlMember();
            jpqlMember2.setUsername("관리자2");
            jpqlMember2.setAge(10);
            jpqlMember2.changeTeam(jpqlTeam);
            jpqlMember2.setRole(RoleType.ADMIN);
            em.persist(jpqlMember2);


            em.flush();
            em.clear();

//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금' " +
//                    "     when m.age >= 60 then '경로요금' " +
//                    "     else '일반요금' " +
//                    "end " +  // 🔥 'end' 필수 추가
//                    "from JpqlMember m";
//
//            List<String> results = em.createQuery(query, String.class).getResultList();
//            for (String result : results) {
//                System.out.println("요금 유형: " + result);
//            }
//
//            String query = "select coalesce(m.username, '이름이 없는 회원') " +
//                    "from JpqlMember m";
//            String query = "select nullif(m.username, '관리자') " +
//                    "from JpqlMember m";

            String query = "select function('group_concat', m.username) from JpqlMember m";
            String singleResult = em.createQuery(query, String.class).getSingleResult();
            System.out.println(singleResult);
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
