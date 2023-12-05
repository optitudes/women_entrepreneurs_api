package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserProfileDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.MicroSiteServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.UserServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.security.TokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final MicroSiteServiceImpl micrositeServiceImpl;


    @PostMapping("/register")
    public ResponseEntity<MessageDTO> register(@Valid @RequestBody UserRegisterRequestDTO userInfo)  throws Exception{
        try{
            Boolean isMicrositeRequesting = userInfo.getMicrosite() != null;
            String message = "Registro exitoso, revisa el correo que te hemos enviado para verificar la cuenta";

            if(isMicrositeRequesting)
                micrositeServiceImpl.validateMicrositeRegisterDto(userInfo.getMicrosite());
            userServiceImpl.registerUser(userInfo.getUser());
            if(isMicrositeRequesting){
                String email = userInfo.getUser().getEmail();
                micrositeServiceImpl.registerMicroSite(userInfo.getMicrosite(),email);
                message+= "\nLos administradores verificarán tu solicitud de micrositio y responderán al correo";
            }
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,message,null ));

        } catch (Exception e) {
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error\n"+e.getMessage(),null ));
        }
    }
    @GetMapping("/general/getUserInfo")
    public ResponseEntity<MessageDTO> getUserInfo(HttpServletRequest request){
        try{
            String email = TokenUtils.getEmailFromAuthorization(request.getHeader("Authorization"));
            UserProfileDTO userInfo = userServiceImpl.getUserProfileInfo(email);
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"Obtencion de informacion del usuraio correcta",userInfo ));

        } catch (Exception e) {
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error",e.getMessage() ));
        }

    }



    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_ROOT')")
    @PostMapping("/testadmin")
    public ResponseEntity<MessageDTO> testadmin(){

        return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"test exitoso admin","test data" ));

    }
    @PreAuthorize("hasRole('ROLE_EMPRENDEDOR') OR hasRole('ROLE_ROOT')")
    @PostMapping("/testemprendedora")
    public ResponseEntity<MessageDTO> testemprendedora(){

        return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"test exitoso emprendedora","test data" ));

    }
    @PostMapping("/testclient")
    public ResponseEntity<MessageDTO> testclient(){

        return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"test exitoso client","test data" ));

    }
}
