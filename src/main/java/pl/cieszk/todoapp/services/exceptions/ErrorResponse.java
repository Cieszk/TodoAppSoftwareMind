package pl.cieszk.todoapp.services.exceptions;

import lombok.Getter;
import lombok.Setter;

public class ErrorResponse {
    @Getter
    @Setter
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
