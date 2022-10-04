package com.example.alkemychallengejava.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

    public Date convertStringToDate(String date){
        Date retDate;
        try {
           retDate = new SimpleDateFormat("dd/DD/yyyy").parse(date);
        } catch (Exception e){
            throw new RuntimeException("The conversion to date couldn't be made");
        }

        return retDate;
    }
}
