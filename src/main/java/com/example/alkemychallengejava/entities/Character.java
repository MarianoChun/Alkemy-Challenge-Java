package com.example.alkemychallengejava.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private Double weight;
    private String story;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "characters_movies",
    joinColumns = {
            @JoinColumn(name = "character_id", referencedColumnName = "id")
    },
            inverseJoinColumns = {
                @JoinColumn(name = "movie_id", referencedColumnName = "id")
            }
    )
    private List<Movie> moviesAssociated;
}
