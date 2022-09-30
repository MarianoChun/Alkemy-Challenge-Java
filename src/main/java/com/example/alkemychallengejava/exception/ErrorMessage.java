package com.example.alkemychallengejava.exception;


public enum ErrorMessage {
    RESOURCE_NOT_FOUND("Error: The resource was not found"),
    RESOURCE_ALREADY_EXISTS(("Error: The resource already exists")),
    RESOURCE_HAS_ID(("Error: The resource has an id")),
    RESOURCE_HAS_NO_ID(("Error: The resource has no id"));

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
