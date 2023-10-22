package co.edu.uniquindio.women_entrepeneurs_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {
    @JsonProperty("userInfo")
    @NotNull
    private UserRegisterDTO user;

    @JsonProperty("microSite")
    @Nullable
    private MicrositeRegisterDTO microsite;
}
