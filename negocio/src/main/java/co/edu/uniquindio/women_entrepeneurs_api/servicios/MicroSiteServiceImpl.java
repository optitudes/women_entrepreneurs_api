package co.edu.uniquindio.women_entrepeneurs_api.servicios;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MicrositeRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.UpdateSolicitudeStatusDTO;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.*;
import co.edu.uniquindio.women_entrepeneurs_api.repo.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MicroSiteServiceImpl implements MicroSiteService {
    private final MicroSiteRepo microSiteRepo;
    private final MailServiceImpl mailService;
    private final VentureRepo ventureRepo;
    private final UserRepo userRepo;
    private final ProfileRepo profileRepo;
    private final LevelAccessRepo levelAccessRepo;
    private final MicroSiteSolicitudeRepo solicitudeRepo;



    public MicroSiteServiceImpl(MicroSiteRepo microSiteRepo, MailServiceImpl mailService, VentureRepo ventureRepo, UserRepo userRepo, ProfileRepo profileRepo, LevelAccessRepo levelAccessRepo, MicroSiteSolicitudeRepo solicitudeRepo) {
        this.microSiteRepo = microSiteRepo;
        this.mailService = mailService;
        this.ventureRepo = ventureRepo;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
        this.levelAccessRepo = levelAccessRepo;
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
        venture.setIsPublish(false);

        Venture ventureSaved = ventureRepo.save(venture);

        MicroSite microSite = new MicroSite();
        microSite.setDescription(microSiteInfo.getMicroSiteDescription());
        microSite.setName(microSiteInfo.getMicrositeName());
        microSite.setIsActive(false);
        microSite.setIsPublish(false);
        microSite.setVenture(ventureSaved);

        MicroSite micrositeSaved = microSiteRepo.save(microSite);
        MicroSiteSolicitude solicitude = new MicroSiteSolicitude();
        solicitude.setUser(profile);
        solicitude.setStatus(MicroSiteSolicitudeStatus.PENDIENTE);
        solicitude.setMicroSite(micrositeSaved);
        solicitude.setVenture(ventureSaved);
        solicitudeRepo.save(solicitude);

        //notificaciones
        mailService.sendMicrositeNotificationClient(user.getEmail(), micrositeSaved.getName(),ventureSaved.getName(),MicroSiteSolicitudeStatus.PENDIENTE.toString());
        List<String> adminEmails = userRepo.findAdminsEmail();
        if (adminEmails != null)
            mailService.sendMicrositeNotificationAdmin(adminEmails,profile.getNames(),micrositeSaved.getName(),ventureSaved.getName());
        return true;
    }

    @Override
    public void updateMicroSiteSolicitudeStatus(UpdateSolicitudeStatusDTO newStatus, String adminEmail) throws Exception {
        Optional<User> adminOptional = userRepo.findByEmail(adminEmail);
        Optional<MicroSiteSolicitude> solicitudeOptional = solicitudeRepo.findMicroSiteSolicitudeById(newStatus.getId());
        boolean isActive = false;
        LevelAccess userAccess = null;

        if(adminOptional.isEmpty())
            throw new Exception("Usuario administrador no disponible");
        if(solicitudeOptional.isEmpty())
            throw  new Exception("Solicitud no encontrada");

        User admin = adminOptional.get();
        MicroSiteSolicitude solicitude = solicitudeOptional.get();

        //se valida si es posible actualizar el estado de la solicitud
        validateChangeSolicitudeStatus(admin,solicitude);
        //se actualiza la activacion del micrositio y la empresa
        if(newStatus.getStatus() == MicroSiteSolicitudeStatus.APROBADO){
            Optional<LevelAccess> emprendedorAccess = levelAccessRepo.findByAccessCode(2001);
            if(emprendedorAccess.isEmpty())
                throw new Exception("Ocurrio un error al cambiar el usuario a emprendedor");

            isActive = true;
            userAccess =emprendedorAccess.get();
        }else{
            Optional<LevelAccess> clientAccess = levelAccessRepo.findByAccessCode(3001);
            if(clientAccess.isEmpty())
                throw new Exception("Ocurrio un error al cambiar el usuario a emprendedor");

            isActive = false;
            userAccess =clientAccess.get();
        }
        //obtenemos el usuario que hizo la solicitud
        User user = solicitude.getUser().getUser();
        user.setLevelAccess(userAccess);
        userRepo.save(user);
        //cambiamos los estados de actividad del micro sitio y de la micro empresa
        MicroSite microSite = solicitude.getMicroSite();
        microSite.setIsActive(isActive);
        microSiteRepo.save(microSite);
        Venture venture = solicitude.getVenture();
        venture.setIsActive(isActive);
        ventureRepo.save(venture);
        //modificamos los atributos de la solicitud
        solicitude.setStatus(newStatus.getStatus());
        solicitude.setAdmin(admin.getProfile());
        //verificamos hay un comentario
        if(!newStatus.getComment().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String commentDate = sdf.format(new Date());
            String comment = "fecha comentario ["+commentDate+"]\n"+newStatus.getComment();
            solicitude.setComment(solicitude.getComment()+"\n\n"+comment);
        }
        solicitudeRepo.save(solicitude);
        //notificamos al usuario
        mailService.sendMicrositeUpdatedSolicitudeNotificationClient(solicitude.getUser().getUser().getEmail()
                                                                    ,solicitude.getMicroSite().getName(),
                                                                     newStatus.getComment().isEmpty()?"":newStatus.getComment(),
                                                                     String.valueOf(newStatus.getStatus()));

    }

    //este metodo se asegura de que la solicitud puede ser modificada
    private void validateChangeSolicitudeStatus(User admin, MicroSiteSolicitude solicitude) throws Exception {
        if(solicitude.getAdmin() != null && !Objects.equals(solicitude.getAdmin().getId(), admin.getProfile().getId()))
            throw new Exception("La solicitud ya está siendo procesada por otro administrador");
    }
}
