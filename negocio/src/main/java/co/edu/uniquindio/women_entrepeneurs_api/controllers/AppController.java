package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.AdminMicroSiteSolicitudeRequestDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.microsite.UpdateSolicitudeStatusDTO;
import co.edu.uniquindio.women_entrepeneurs_api.repo.TourisRouteRepo;
import co.edu.uniquindio.women_entrepeneurs_api.security.TokenUtils;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.ImageServiceImpl;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.MicroSiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/appinfo")
@CrossOrigin
public class AppController {


    @Autowired
    private final ImageServiceImpl imageService;

    @GetMapping("/public/getAppImage/{imageName}")
    private ResponseEntity<byte[]> getAppImage(@PathVariable(name = "imageName") String imageName) {
        try {
            byte[] image =  imageService.getAppImage(imageName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}


