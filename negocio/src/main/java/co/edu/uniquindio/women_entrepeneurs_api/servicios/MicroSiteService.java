package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MicrositeRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.UpdateSolicitudeStatusDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitude;

import java.util.List;

public interface MicroSiteService {
    void validateMicrositeRegisterDto(MicrositeRegisterDTO microSite) throws Exception;
    List<AdminMicroSiteSolicitudeDTO> getSolicitudesWithFilters(AdminMicroSiteSolicitudeRequestDTO filters) throws Exception;
    Boolean registerMicroSite(MicrositeRegisterDTO microSite,String email) throws Exception;
    void updateMicroSiteSolicitudeStatus(UpdateSolicitudeStatusDTO newStatus, String adminEmail) throws Exception;
}
