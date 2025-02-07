package helloJpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member  {

 @Id @GeneratedValue
    private Long id;

 @Column(name = "USERNAME")
    private String name;

@Embedded
private Period workPeriod;

@Embedded
private Address homeAddress;

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

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
