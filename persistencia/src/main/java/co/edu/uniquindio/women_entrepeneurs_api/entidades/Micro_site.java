package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Micro_site implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "descriptio", nullable = true, length = 100)
    private String description;

    /* Preguntar si productos y servicios puede ser una clase aparte
       Con sus atributos como nombre, descripción, precio, etc
     */
    @Column(name = "products")
    private String products;

    @Column(name = "services")
    private String services;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "experiences", nullable = true)
    private String experiences;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Micro_site microSite = (Micro_site) o;
        return Objects.equals(id, microSite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
