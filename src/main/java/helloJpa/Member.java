package helloJpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

 @Id @GeneratedValue
    private Long id;

 @Column(name = "USERNAME")
    private String name;

 @ManyToOne
 @JoinColumn(name = "TEAM_ID")
 private Team team;

//    @Column(name = "TEAM_ID")
//    private Long teamId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
