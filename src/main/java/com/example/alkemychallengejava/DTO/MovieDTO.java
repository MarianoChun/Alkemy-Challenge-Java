package com.example.alkemychallengejava.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDTO implements Serializable {
    private Long id;
    private String image;
    private String title;
    private String creationDate;
}
