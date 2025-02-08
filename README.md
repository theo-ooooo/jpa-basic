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


- JPQL 사용시 flush() 가 실행하고 쿼리실행된다.


### 플러시는!
- 영속성 컨텍스트를 비우지 않음
- 영속성 컨텍스트의 변경내용을 디비에 동기화


### 데이터베이스 스키마 자동생성
- create (드랍후 생성)
- create-drop (드랍후 생성후 드랍)
- update (변경내용만 저장)
- validate (테이블에 있는 퀄럼만 연동)

### 자바 enum 타입 매핑할때 사용
- 주의 : ORDINAL 사용X
- enum 순서를 데이터베이스의 저장함 (0,1,2)

### 연관관계의 주인(Owner)
- 객체의 두관계중 하나를 주인으로 지정
- 주인이 아닌쪽은 읽기만 가능하고
- 주인은 mappedBy 속성 X
- 주인이 아니면 mappedBy 속성 으로 주인 지정

### 다대다 매핑의 한계
- 편리해 보이지만 실무에서 사용할수 없다.
- 단순히 FK만 필요하지 않고 추가적인 퀄럼들이 필요.
- 연결 테이블용 엔티티를 추가한다.
- @ManyToMany -> @OneToMany, @ManyToOne

### 지연로딩, 즉시로딩
- 실무에서 가급적 지연로딩만 사용.
- @OneToMany @ManyToMany 는 기본이 지연로딩
- @ManyToOne, @OneToOne은 기본이 즉시로딩

## 경로 포현식 특징
- 단일값 연관 경로 : 묵시적 내부 조인 (SELECT 절에서 m.team 을 할경우 조인이 됨) 이렇게 쓰면 안된다고함.
  - 생각했을때, 협업시에 힘들꺼같음.
- 컬렉션 값 연관 경로 : 이것도 묵시적 내부조인 발생.
- t.members.size로 강의에서 하는데 되질 않는다. (size(t.members) 로 하면 날라감.)


## 패치 조인과 일반 조인 차이점
- 패치 조인은 select 절에 명시를 하지 않아도, 자동으로 조회가 된다. (레이지 정책이어도 이거로 바로 불러온다.)
- 일반조인은 레이지 정책이면 불러오지 않는다. (이거면 불러오는걸로 확인)