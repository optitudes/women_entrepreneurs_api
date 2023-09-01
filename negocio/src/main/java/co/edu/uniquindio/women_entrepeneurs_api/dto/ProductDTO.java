package co.edu.uniquindio.women_entrepeneurs_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
@Data
@AllArgsConstructor
public class ProductDTO {
    private Integer id;

    private String image;

    private String name;

    private String description;

    private Double price;

    private Boolean isAvailable;

    private Integer stacks;

    private Date limitDate;

    private Boolean isActive;

}
