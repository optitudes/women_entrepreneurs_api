package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserProfileDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.UserRegisterRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.ImageServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.MicroSiteServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.UserServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.security.TokenUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final MicroSiteServiceImpl micrositeServiceImpl;
    private final ImageServiceImpl imageService;


    @PostMapping("/register")
    public ResponseEntity<MessageDTO> register(@Valid @RequestBody UserRegisterRequestDTO userInfo) throws Exception {
        try {
            Boolean isMicrositeRequesting = userInfo.getMicrosite() != null;
            String message = "Registro exitoso, revisa el correo que te hemos enviado para verificar la cuenta";

            if (isMicrositeRequesting)
                micrositeServiceImpl.validateMicrositeRegisterDto(userInfo.getMicrosite());
            userServiceImpl.registerUser(userInfo.getUser());
            if (isMicrositeRequesting) {
                String email = userInfo.getUser().getEmail();
                micrositeServiceImpl.registerMicroSite(userInfo.getMicrosite(), email);
                message += "\nLos administradores verificarán tu solicitud de micrositio y responderán al correo";
            }
            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, true, message, null));

        } catch (Exception e) {
            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, false, "Ocurrió un error\n" + e.getMessage(), null));
        }
    }

    @GetMapping("/general/getUserInfo")
    public ResponseEntity<MessageDTO> getUserInfo(HttpServletRequest request) {
        try {
            String email = TokenUtils.getEmailFromAuthorization(request.getHeader("Authorization"));
            UserProfileDTO userInfo = userServiceImpl.getUserProfileInfo(email);
            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, true, "Obtencion de informacion del usuraio correcta", userInfo));

        } catch (Exception e) {
            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, false, "Ocurrió un error", e.getMessage()));
        }
    }

    @PostMapping("/general/updateProfilePicture")
    private ResponseEntity<MessageDTO> updateProfilePicture(@RequestParam("image") MultipartFile image,HttpServletRequest request) {
        try {
            String email = TokenUtils.getEmailFromAuthorization(request.getHeader("Authorization"));
            imageService.uploadProfileImage(image,email);
            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, true, "Imagen actualizada con exito", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(200).body(new MessageDTO(HttpStatus.OK, false, "Ocurrió un error\n" + e.getMessage(), null));
        }
    }

    @GetMapping("/public/getProfilePicture/{imageName}")
    private ResponseEntity<byte[]> getProfilePicture(@PathVariable(name = "imageName") String imageName) {
        try {
            byte[] image =  imageService.getProfileImage(imageName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
