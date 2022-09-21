package com.example.alkemychallengejava.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    private Date creationDate;
    private Integer rating;
    @ManyToMany(mappedBy = "moviesAssociated", fetch = FetchType.LAZY)
    private List<Character> charactersAssociated;

    public Movie(Long id, String image, String title, Date creationDate, Integer rating, List<Character> charactersAssociated) {
        if(!(rating >= 1 && rating <= 5)){
            throw new IllegalArgumentException("La calificación debe estar entre el 1 y el 5");
        }

        this.id = id;
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
        this.rating = rating;
        this.charactersAssociated = charactersAssociated;
    }
}
