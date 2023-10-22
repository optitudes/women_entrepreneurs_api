package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MicrositeRegisterDTO;

public interface MicroSiteService {
    void validateMicrositeRegisterDto(MicrositeRegisterDTO microSite) throws Exception;
    Boolean registerMicroSite(MicrositeRegisterDTO microSite,String email) throws Exception;
}
