package co.edu.uniquindio.women_entrepeneurs_api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestDTO {
    @NotNull
    private  String token;
    @NotNull
    private  String newPassword;
}
