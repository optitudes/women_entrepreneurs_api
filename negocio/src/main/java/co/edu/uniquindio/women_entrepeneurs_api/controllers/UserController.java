package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterDTO;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<MessageDTO> register(@Valid @RequestBody UserRegisterDTO userInfo)  throws Exception{
        try{
            Boolean isRegistered = userServiceImpl.registerUser(userInfo);
            if(isRegistered){
                return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"Registro exitoso, revisa el correo que te hemos enviado para verificar la cuenta",null ));
            }else{
                return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error al registrar el usuario en la base de datos",null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error",e.getMessage() ));
        }
    }
}
