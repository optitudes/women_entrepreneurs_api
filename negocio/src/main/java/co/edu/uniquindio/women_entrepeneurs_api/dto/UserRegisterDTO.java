package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String idNumber;
    @NotNull
    private String names;
    @NotNull
    private String lastNames;
    @Nullable
    private String address;
    @Nullable
    private String phoneNumber;
}
