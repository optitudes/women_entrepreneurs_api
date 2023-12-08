package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MicrositeRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.*;
import co.edu.uniquindio.women_entrepeneurs_api.repo.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MicroSiteServiceImpl implements MicroSiteService {
    private final MicroSiteRepo microSiteRepo;
    private final MailServiceImpl mailService;
    private final VentureRepo ventureRepo;
    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;
    private final MicroSiteSolicitudeRepo solicitudeRepo;



    public MicroSiteServiceImpl(MicroSiteRepo microSiteRepo, MailServiceImpl mailService, VentureRepo ventureRepo, UserRepo userRepo, ProfileRepo profileRepo, MicroSiteSolicitudeRepo solicitudeRepo) {
        this.microSiteRepo = microSiteRepo;
        this.mailService = mailService;
        this.ventureRepo = ventureRepo;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
        this.solicitudeRepo = solicitudeRepo;
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
    public List<AdminMicroSiteSolicitudeDTO> getSolicitudesWithFilters(AdminMicroSiteSolicitudeRequestDTO filters) throws Exception {
        List<MicroSiteSolicitude> microSiteSolicitudes = solicitudeRepo.obtainWithFilters(filters.getStatus());
        ArrayList<AdminMicroSiteSolicitudeDTO> solicitudes = new ArrayList<>();

        for(MicroSiteSolicitude solicitude: microSiteSolicitudes){
            AdminMicroSiteSolicitudeDTO solicitudeFormated = new AdminMicroSiteSolicitudeDTO();
            solicitudeFormated.setId(solicitude.getId());
            solicitudeFormated.setMicroSiteName(solicitude.getMicroSite().getName());
            solicitudeFormated.setVentureName(solicitude.getVenture().getName());
            solicitudeFormated.setUserName(solicitude.getUser().getNames());
            solicitudeFormated.setUserLastName(solicitude.getUser().getLastNames());
            solicitudeFormated.setStatus(solicitude.getStatus());
            solicitudeFormated.setComment(solicitude.getComment());
            solicitudes.add(solicitudeFormated);
        }

        return solicitudes;
    }

    @Override
    public Boolean registerMicroSite(MicrositeRegisterDTO microSiteInfo, String email) throws Exception {

        User user = userRepo.findByEmail(email).get();
        Optional<Profile> profileOptional = profileRepo.findProfileByUser(user);
        Profile profile = profileOptional.get();

        Venture venture = new Venture();
        venture.setAddress(microSiteInfo.getVentureAddress());
        venture.setDescription(microSiteInfo.getVentureDescription());
        venture.setMapLatitude(microSiteInfo.getVentureMapLatitude());
        venture.setMapLongitude(microSiteInfo.getVentureMapLongitude());
        venture.setName(microSiteInfo.getVentureName());
        venture.setUser(user);
        venture.setIsActive(false);

        Venture ventureSaved = ventureRepo.save(venture);

        MicroSite microSite = new MicroSite();
        microSite.setDescription(microSiteInfo.getMicroSiteDescription());
        microSite.setName(microSiteInfo.getMicrositeName());
        microSite.setIsActive(false);
        microSite.setVenture(ventureSaved);

        MicroSite micrositeSaved = microSiteRepo.save(microSite);
        MicroSiteSolicitude solicitude = new MicroSiteSolicitude();
        solicitude.setUser(profile);
        solicitude.setStatus(MicroSiteSolicitudeStatus.PENDIENTE);
        solicitude.setMicroSite(micrositeSaved);
        solicitude.setVenture(ventureSaved);
        solicitudeRepo.save(solicitude);

        //notificaciones
        mailService.sendMicrositeNotificationClient("optt.itudes@gmail.com",micrositeSaved.getName(),ventureSaved.getName(),MicroSiteSolicitudeStatus.PENDIENTE.toString());
        mailService.sendMicrositeNotificationAdmin("optt.itudes@gmail.com",profile.getNames(),micrositeSaved.getName(),ventureSaved.getName());

        return true;
    }
}
