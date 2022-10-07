package com.example.alkemychallengejava.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;


    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private Set<Movie> moviesAssociated = new HashSet<>();

    public Genre(String name, String image) {
        this.name = name.toLowerCase();
        this.image = image;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
