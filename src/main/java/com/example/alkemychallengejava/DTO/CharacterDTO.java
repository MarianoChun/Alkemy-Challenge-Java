package com.example.alkemychallengejava.DTO;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacterDTO implements Serializable {
    private Long id;
    private String image;
    private String name;
}
