package com.example.alkemychallengejava.exception;

import java.text.MessageFormat;


public enum ErrorMessage {
    CHARACTER_NOT_FOUND("Error: The character was not found"),
    CHARACTER_ALREADY_EXISTS(("Error: The character already exists")),
    CHARACTER_HAS_ID(("Error: The character has an id")),
    CHARACTER_HAS_NO_ID(("Error: The character has no id"));

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
