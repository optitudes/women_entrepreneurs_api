package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.MicroSiteServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
