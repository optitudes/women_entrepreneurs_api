package co.edu.uniquindio.women_entrepeneurs_api.dto;

public class ErrorDTO {
    private String message;
    private int status;

    public ErrorDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }

    // Getters y setters
}
