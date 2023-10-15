package co.edu.uniquindio.women_entrepeneurs_api.exceptions;

import co.edu.uniquindio.women_entrepeneurs_api.dto.LoginResponseDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationEntryPointException implements AuthenticationEntryPoint {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {


        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        MessageDTO<String> responseContent = new MessageDTO<>(HttpStatus.FORBIDDEN,false,"Autenticación fallida :"+ authException.getMessage(),null);
        response.getWriter().write(objectMapper.writeValueAsString(responseContent));
    }
}
