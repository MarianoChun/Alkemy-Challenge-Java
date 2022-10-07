package com.example.alkemychallengejava.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(
        name = "character_table",
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String story;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "characters_movies",
    joinColumns = {
            @JoinColumn(name = "character_id", referencedColumnName = "id")
    },
            inverseJoinColumns = {
                @JoinColumn(name = "movie_id", referencedColumnName = "id")
            }
    )
    private Set<Movie> moviesAssociated = new HashSet<>();

    public Character(Long id, String image, String name, Integer age, Double weight, String story, Set<Movie> moviesAssociated) {
        this.id = id;
        this.image = image;
        this.name = name.toLowerCase();
        this.age = age;
        this.weight = weight;
        this.story = story;
        this.moviesAssociated = moviesAssociated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return name.equals(character.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
