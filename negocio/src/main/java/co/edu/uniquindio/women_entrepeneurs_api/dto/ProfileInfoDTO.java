package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public class ProfileInfoDTO {
    private String token;
    private String email;
    private Integer accessCode;
    private String accessName;
    private String accessDescription;
    @Nullable
    private String username;
}
