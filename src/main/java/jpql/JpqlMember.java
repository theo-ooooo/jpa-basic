package jpql;

import jakarta.persistence.*;

@Entity
@NamedQuery(
        name = "Member.findByUsername",
        query = "select m from JpqlMember m where m.username = :username"
)
public class JpqlMember {
    @Id @GeneratedValue
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private JpqlTeam team;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public JpqlTeam getTeam() {
        return team;
    }

    public void setTeam(JpqlTeam team) {
        this.team = team;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void changeTeam(JpqlTeam team) {
        this.team = team;
        team.getMembers().add(this);
    }

    @Override
    public String toString() {
        return "JpqlMember{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
