package helloJpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Team extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;

}
