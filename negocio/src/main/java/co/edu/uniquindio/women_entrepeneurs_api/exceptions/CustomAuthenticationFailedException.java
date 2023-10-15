package co.edu.uniquindio.women_entrepeneurs_api.exceptions;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationFailedException implements AuthenticationFailureHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        MessageDTO<String> responseContent = new MessageDTO<>(HttpStatus.FORBIDDEN,false,"Autenticación fallida :"+ exception.getMessage(),null);
        response.getWriter().write(objectMapper.writeValueAsString(responseContent));
    }
}
