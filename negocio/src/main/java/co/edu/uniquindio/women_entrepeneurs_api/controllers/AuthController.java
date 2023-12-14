package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.VerifyEmailTokenDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.user.GetTokenResetPasswordRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.user.ResetPasswordRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.security.TokenUtils;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.MailServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.UserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {

    private final UserServiceImpl userServiceImpl;
    private final MailServiceImpl mailService;


    @PostMapping("/login")
    public ResponseEntity<MessageDTO> login(@Valid @RequestBody LoginRequestDTO loginInfo){
        try {
            LoginResponseDTO userInfo = userServiceImpl.login(loginInfo);
            String token = getJWTToken(userInfo.getEmail(),userInfo.getAccessName());
            userInfo.setToken(token);
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"login exitoso",userInfo ));

        } catch (Exception e) {
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error\n"+e.getMessage(),null ));
        }

    }
    @PostMapping("/verifyEmail")
    public ResponseEntity<MessageDTO> verifYEmail(@Valid @RequestBody VerifyEmailTokenDTO token){
        try{
            String email = TokenUtils.decryptAES(token.getToken());
            userServiceImpl.verifyEmail(email);
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"Email validado correctamente",email ));

        } catch(Exception e){
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error\n"+e.getMessage(),null ));
        }
    }
    @PostMapping("/generateResetPasswordToken")
    public ResponseEntity<MessageDTO> generateResetPasswordToken(@Valid @RequestBody GetTokenResetPasswordRequestDTO email){
        try{
            userServiceImpl.sendPasswordResetToken(email.getEmail());
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"token de reestablecimiento de clave enviado",null ));

        } catch(Exception e){
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error\n"+e.getMessage(),null ));
        }
    }
    @PostMapping("/resetPassword")
    public ResponseEntity<MessageDTO> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO resetPasswordInfo){
        try{
            userServiceImpl.resetPassword(resetPasswordInfo.getToken(),resetPasswordInfo.getNewPassword());
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"Clave cambiada correctamente",null ));

        } catch(Exception e){
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error\n"+e.getMessage(),null ));
        }
    }

    private String getJWTToken(String email,String role) {
        String secretKey = "estaEsMiClaveSecretaCon512BitsDeLongitudParaHS512a;lsdkj;alksdj309708d9d9d9n";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_"+role);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(email)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
