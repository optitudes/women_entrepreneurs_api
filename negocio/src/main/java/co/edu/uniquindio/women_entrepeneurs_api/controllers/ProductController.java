package co.edu.uniquindio.women_entrepeneurs_api.controllers;

import co.edu.uniquindio.women_entrepeneurs_api.dto.MessageDTO;
import co.edu.uniquindio.women_entrepeneurs_api.dto.ProductDTO;
import co.edu.uniquindio.women_entrepeneurs_api.servicios.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/search/{pattern}")
    public ResponseEntity<MessageDTO> searchProducts(@PathVariable String pattern){
        try {
            List<ProductDTO> productList = productServiceImpl.findByNameOrPrice(pattern);
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, true,"Productos cargados con exito",productList ));

        } catch (Exception e) {
            return ResponseEntity.status(200).body( new MessageDTO(HttpStatus.OK, false,"Ocurrió un error",e.getMessage() ));
        }
    }

}
