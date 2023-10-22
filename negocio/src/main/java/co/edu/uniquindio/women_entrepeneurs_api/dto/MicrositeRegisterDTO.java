package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MicrositeRegisterDTO {
    @Nullable
    private String ventureAddress;
    @Nullable
    private String ventureDescription;
    @Nullable
    private Double ventureMapLatitude;
    @Nullable
    private Double ventureMapLongitude;
    @NotNull
    private String ventureName;
    @Nullable
    private String microSiteAddress;
    @Nullable
    private String microSiteDescription;
    @Nullable
    private String micrositeExperiences;
    @NotNull
    private String micrositeName;

}
