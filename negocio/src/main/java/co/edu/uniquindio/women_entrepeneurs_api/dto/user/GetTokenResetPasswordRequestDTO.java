package co.edu.uniquindio.women_entrepeneurs_api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenResetPasswordRequestDTO {
    private String email;
}
