package ru.practicum.ewmservice.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException(final String message) {
        super(message);
    }
}
