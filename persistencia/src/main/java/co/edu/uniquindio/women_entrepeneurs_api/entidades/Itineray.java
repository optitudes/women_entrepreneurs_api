package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Itineray implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itineray itineray = (Itineray) o;
        return Objects.equals(id, itineray.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
