package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Map implements Serializable {

    /**
     * La tabla LocationSite también tendría relación con esta tabla?
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "mapImg", nullable = false)
    private String mapImg;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "map")
    private List<LocationSite> locationSiteList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Map map = (Map) o;
        return Objects.equals(id, map.id) && Objects.equals(name, map.name) && Objects.equals(description, map.description) && Objects.equals(latitude, map.latitude) && Objects.equals(longitude, map.longitude) && Objects.equals(mapImg, map.mapImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, latitude, longitude, mapImg);
    }
}
