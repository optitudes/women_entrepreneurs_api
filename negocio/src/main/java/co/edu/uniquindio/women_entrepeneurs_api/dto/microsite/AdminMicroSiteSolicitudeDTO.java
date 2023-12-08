package co.edu.uniquindio.women_entrepeneurs_api.dto.microsite;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitudeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminMicroSiteSolicitudeDTO {

    @NotNull
    private Long id;
    @NotNull
    private MicroSiteSolicitudeStatus status;
    @NotNull
    private String microSiteName;
    @NotNull
    private String userName;
    @NotNull
    private String userLastName;
    @NotNull
    private String ventureName;
    private String comment;

}
