package com.example.alkemychallengejava.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "personajes_peliculas",
    joinColumns = {
            @JoinColumn(name = "personaje_id", referencedColumnName = "id")
    },
            inverseJoinColumns = {
                @JoinColumn(name = "pelicula_id", referencedColumnName = "id")
            }
    )
    private List<Pelicula> peliculasAsociadas;
}
