package co.edu.uniquindio.women_entrepeneurs_api.exceptions;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@ControllerAdvice
public class CustomAccessDeniedHandlerException implements org.springframework.security.web.access.AccessDeniedHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        MessageDTO<String> responseContent = new MessageDTO<>(HttpStatus.FORBIDDEN,false,"Autenticación fallida :"+ e.getMessage(),null);
        response.getWriter().write(objectMapper.writeValueAsString(responseContent));

    }
}
