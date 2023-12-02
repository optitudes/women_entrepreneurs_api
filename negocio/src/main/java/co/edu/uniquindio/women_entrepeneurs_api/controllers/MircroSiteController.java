package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MicroSiteDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Image;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSite;
import co.edu.uniquindio.women_entrepeneurs_api.repo.MicroSiteRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.TourisRouteRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/microsite")
@CrossOrigin
public class MircroSiteController {

    @Autowired
    private final MicroSiteRepo microSiteRepository;
    @Autowired
    private final TourisRouteRepo tourisRouteRepo;

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

}


