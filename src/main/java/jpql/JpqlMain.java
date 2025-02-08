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

//            String query = "select m from JpqlMember m join fetch m.team";
            String query = "select m from JpqlMember m join m.team";
            List<JpqlMember> resultList = em.createQuery(query, JpqlMember.class).getResultList();
            System.out.println(resultList.size());

            for (JpqlMember member : resultList) {
                System.out.println(member);
            }
//
//            for (JpqlTeam team : resultList) {
//                System.out.println("member = " + team.getName() + ", " + team.getMembers().size());
//                List<JpqlMember> members = team.getMembers();
//                for (JpqlMember member : members) {
//                    System.out.println("member = " + member);
//                }
//            }
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
