package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.MicroSiteDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserProfileDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Image;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSite;
import co.edu.uniquindio.women_entrepeneurs_api.repo.MicroSiteRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.TourisRouteRepo;
import co.edu.uniquindio.women_entrepeneurs_api.security.TokenUtils;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.MicroSiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/microsite")
@CrossOrigin
public class MircroSiteController {

    @Autowired
    private final MicroSiteServiceImpl microSiteService;
    @Autowired
    private final TourisRouteRepo tourisRouteRepo;

    @PostMapping("/admin/getSolicitudes")
    public  ResponseEntity<MessageDTO>getMicrositeSolicitudes(@Valid @RequestBody AdminMicroSiteSolicitudeRequestDTO filters){
        try {
            List<AdminMicroSiteSolicitudeDTO> listSolicitudes = microSiteService.getSolicitudesWithFilters(filters);

            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, true, "Obtencion de informacion de los micrositios correcta", listSolicitudes));

        } catch (Exception e) {
            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, false, "Ocurrió un error", e.getMessage()));
        }
    }
/*
    @GetMapping("/users")
    public List<MicroSiteDTO> getMircroSites() {
        List<MicroSite> microSites = microSiteRepository.findLastSixByIsActiveTrue();
        List<MicroSiteDTO> microSiteDTOs = new ArrayList<>();

        for (MicroSite microSite : microSites) {
            MicroSiteDTO microSiteDTO = new MicroSiteDTO();
            microSiteDTO.setName(microSite.getName());

            if (!microSite.getImages().isEmpty()) {
                Image firstImage = microSite.getImages().get(0);
                microSiteDTO.setImageUrl(firstImage.getName());
            }

            long count = tourisRouteRepo.countByLocationSiteList(microSite);
            microSiteDTO.setTouristRouteCount(count);

            microSiteDTOs.add(microSiteDTO);
        }

        return microSiteDTOs;
    }
    */

}


