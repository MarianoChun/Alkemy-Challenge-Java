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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    @OneToMany(targetEntity = Movie.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_fk", referencedColumnName = "id")
    private List<Movie> moviesAssociated;

}
