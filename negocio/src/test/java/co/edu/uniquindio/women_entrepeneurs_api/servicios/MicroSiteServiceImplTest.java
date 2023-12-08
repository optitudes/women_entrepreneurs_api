package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.NegocioApplication;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitude;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitudeStatus;
import co.edu.uniquindio.women_entrepeneurs_api.repo.MicroSiteSolicitudeRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes= NegocioApplication.class)
@Transactional
class MicroSiteServiceImplTest {
    @Autowired
    private MicroSiteServiceImpl microSiteService;
    @Autowired
    private MicroSiteSolicitudeRepo microRepo;
    @Test
    void getSolicitudesWithFilters() {
        AdminMicroSiteSolicitudeRequestDTO filters = new AdminMicroSiteSolicitudeRequestDTO();
        filters.setStatus(MicroSiteSolicitudeStatus.RECHAZADO);
        try {
            List<AdminMicroSiteSolicitudeDTO> result = microSiteService.getSolicitudesWithFilters(filters);
            for(AdminMicroSiteSolicitudeDTO solicitude: result){
                System.out.println(solicitude.toString());
            }
            Assertions.assertTrue(true);

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }

    }
}