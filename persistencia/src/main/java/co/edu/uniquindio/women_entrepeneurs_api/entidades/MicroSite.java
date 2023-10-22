package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class MicroSite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name="is_active",nullable = false)
    private Boolean isActive;

    @Column(name = "description", nullable = true, length = 100)
    private String description;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "experiences", nullable = true)
    private String experiences;

    @OneToMany(mappedBy = "microSite")
    private List<Document> documents;

    @OneToMany(mappedBy = "microSite")
    private List<Image> images;

    @OneToMany(mappedBy = "microSite")
    private List<Comment> commentsList;

    @OneToMany(mappedBy = "microSite")
    private List<Blog> blogsList;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MicroSite microSite = (MicroSite) o;
        return Objects.equals(id, microSite.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @OneToMany(mappedBy="microSite")
    private List<TouristRoute> touristRouteList;

    @OneToOne
    private Venture venture;

    @OneToMany(mappedBy="microSite")
    private List<Video> videoList;

}
