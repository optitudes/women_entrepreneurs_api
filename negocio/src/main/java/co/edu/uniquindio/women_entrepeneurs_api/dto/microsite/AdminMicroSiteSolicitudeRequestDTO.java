package co.edu.uniquindio.women_entrepeneurs_api.dto.microsite;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitudeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminMicroSiteSolicitudeRequestDTO {
    private MicroSiteSolicitudeStatus status;

}
