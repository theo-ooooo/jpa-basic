# JPA BASIC

### 영속성 컨텍스트
- 엔티티를 영구 저장하는 환경 이라는뜻.
- 논리적인 개념.
- 눈에 보이지 않음.
- 엔티티 매니저를 통해 접근

### 생명주기
1. 비영속
2. 영속
3. 준영속
4. 삭제


```java
    // 비영속 상태
    Member member = new Member();
    member.setId(1L);
    member.setUsername("강경원");

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    //객체를 저장한 상태(영속)
    em.persist(member);

```
```java
 tx.commit() // 이걸 해야 쿼리를 보냄.
```