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
public class LocationSite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = true, length = 100)
    private String description;

    @Column(name = "url", nullable = false, unique = false)
    private String url;

    @Column(name = "img_url", unique = false, nullable = false)
    private String img_url;

    @Column(name="map_latitude")
    private Double mapLatitude;

    @Column(name="map_longitude")
    private Double mapLongitude;

    @ManyToOne
    private TouristRoute touristRoute;

    @ManyToOne
    private Map map;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationSite that = (LocationSite) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
