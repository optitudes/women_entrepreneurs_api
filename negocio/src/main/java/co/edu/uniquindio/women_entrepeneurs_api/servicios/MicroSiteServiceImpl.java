package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MicrositeRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSite;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Venture;
import co.edu.uniquindio.women_entrepeneurs_api.repo.MicroSiteRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.UserRepo;
import co.edu.uniquindio.women_entrepeneurs_api.repo.VentureRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MicroSiteServiceImpl implements MicroSiteService {
    private final MicroSiteRepo microSiteRepo;
    private final VentureRepo ventureRepo;
    private final UserRepo userRepo;



    public MicroSiteServiceImpl(MicroSiteRepo microSiteRepo, VentureRepo ventureRepo, UserRepo userRepo) {
        this.microSiteRepo = microSiteRepo;
        this.ventureRepo = ventureRepo;
        this.userRepo = userRepo;
    }


    @Override
    public void validateMicrositeRegisterDto(MicrositeRegisterDTO microSite) throws Exception {
        Optional<MicroSite> microSiteSearched = microSiteRepo.findByName(microSite.getMicrositeName());
        if (microSiteSearched.isPresent()){
            throw new Exception("El nombre del micro sitio ya está en uso");
        }
        Optional<Venture> ventureSearched = ventureRepo.findByName(microSite.getVentureName());
        if (ventureSearched.isPresent()){
            throw new Exception("El nombre del emprendimiento ya está en uso");
        }
    }

    @Override
    public Boolean registerMicroSite(MicrositeRegisterDTO microSiteInfo, String email) throws Exception {

        Integer userId = userRepo.findByEmail(email).get().getId();

        Venture venture = new Venture();
        venture.setAddress(microSiteInfo.getVentureAddress());
        venture.setDescription(microSiteInfo.getVentureDescription());
        venture.setMapLatitude(microSiteInfo.getVentureMapLatitude());
        venture.setMapLongitude(microSiteInfo.getVentureMapLongitude());
        venture.setName(microSiteInfo.getVentureName());
        venture.setIsActive(false);

        Venture ventureSaved = ventureRepo.save(venture);

        MicroSite microSite = new MicroSite();
        microSite.setAddress(microSiteInfo.getMicroSiteAddress());
        microSite.setDescription(microSiteInfo.getMicroSiteDescription());
        microSite.setExperiences(microSite.getExperiences());
        microSite.setName(microSiteInfo.getMicrositeName());
        microSite.setIsActive(false);
        microSite.setVenture(ventureSaved);
        microSiteRepo.save(microSite);
        return true;
    }
}
