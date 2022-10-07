package com.example.alkemychallengejava.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
        uniqueConstraints = @UniqueConstraint(columnNames = {"title"})
)
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
    private Set<Character> charactersAssociated = new HashSet<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_fk")
    private Genre genre;

    public Movie(Long id, String image, String title, String creationDate, Integer rating, Set<Character> charactersAssociated, Genre genre) {
        if(!(rating >= 1 && rating <= 5)){
            throw new IllegalArgumentException("The rating must be between 1 and 5");
        }

        this.id = id;
        this.image = image;
        this.title = title.toLowerCase();
        this.creationDate = creationDate;
        this.rating = rating;
        this.charactersAssociated = charactersAssociated;
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public void addCharacterAssociated(Character character){
        charactersAssociated.add(character);
    }
}
