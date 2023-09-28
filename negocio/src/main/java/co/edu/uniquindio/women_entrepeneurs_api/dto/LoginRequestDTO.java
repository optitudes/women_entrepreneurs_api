package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {
    private String idNumber;
    private String password;
}
