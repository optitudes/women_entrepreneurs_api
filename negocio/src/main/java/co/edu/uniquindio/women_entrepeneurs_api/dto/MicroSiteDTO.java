package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MicroSiteDTO {

    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String imageUrl;
    @NotNull
    private long touristRouteCount;

}
