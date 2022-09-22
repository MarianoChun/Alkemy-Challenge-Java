package com.example.alkemychallengejava.DTO;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterDTO implements Serializable {
    private String image;
    private String name;
}
