package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class TouristRoute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="name", length=45,nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "touristRoute")
    private List<LocationSite> locationSiteList;

    @OneToMany(mappedBy = "touristRoute")
    private List<Activiy> activiyList;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TouristRoute that = (TouristRoute) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @OneToOne(mappedBy="touristRoute")
    private Itinerary itinerary;

    @OneToMany(mappedBy="touristRoute")
    private List<Review> reviewList;

    @ManyToOne
    private MicroSite microSite;
}
