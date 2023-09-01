package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class MessageDTO<T> {
    private HttpStatus status;
    private boolean success;
    private String message;
    private T data;
}