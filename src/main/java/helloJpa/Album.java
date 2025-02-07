package helloJpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class Album extends Item {
    private String artist;
}
