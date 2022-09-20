package com.example.alkemychallengejava.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String imagen;
    @OneToMany(targetEntity = Pelicula.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_fk", referencedColumnName = "id")
    private List<Pelicula> peliculasAsociadas;

}
