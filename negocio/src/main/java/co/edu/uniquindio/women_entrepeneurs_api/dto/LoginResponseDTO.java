package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String email;
    @Nullable
    private String username;
}
