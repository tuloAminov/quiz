package ru.students.startup.project.exception;

public class RoomLoginException extends Exception {

    private final String message;
    public RoomLoginException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}