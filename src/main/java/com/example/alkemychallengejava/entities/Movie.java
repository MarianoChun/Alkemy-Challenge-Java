package com.example.alkemychallengejava.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "creation_date")
    private String creationDate;

    private Integer rating;

    @JsonIgnore
    @ManyToMany(mappedBy = "moviesAssociated", fetch = FetchType.LAZY)
    private List<Character> charactersAssociated;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_fk")
    private Genre genre;

    public Movie(Long id, String image, String title, String creationDate, Integer rating, List<Character> charactersAssociated, Genre genre) {
        if(!(rating >= 1 && rating <= 5)){
            throw new IllegalArgumentException("The rating must be between 1 and 5");
        }

        this.id = id;
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
        this.rating = rating;
        this.charactersAssociated = charactersAssociated;
        this.genre = genre;
    }
}
