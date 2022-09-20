package com.example.alkemychallengejava.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private Integer calificacion;
    @ManyToMany(mappedBy = "peliculasAsociadas", fetch = FetchType.LAZY)
    private List<Personaje> personajesAsociados;

    public Pelicula(Long id, String imagen, String titulo, Date fechaCreacion, Integer calificacion, List<Personaje> personajesAsociados) {
        if(!(calificacion >= 1 && calificacion <= 5)){
            throw new IllegalArgumentException("La calificación debe estar entre el 1 y el 5");
        }

        this.id = id;
        this.imagen = imagen;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.calificacion = calificacion;
        this.personajesAsociados = personajesAsociados;
    }
}
