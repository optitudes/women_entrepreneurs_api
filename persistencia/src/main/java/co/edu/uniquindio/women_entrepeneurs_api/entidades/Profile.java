package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="idNumber", length = 45, nullable = false, unique = true)
    private String idNumber;

    @Column(name="names", length=60,nullable = false)
    private String names;

    @Column(name="last_names", length=60,nullable = false)
    private String lastNames;

    @Column(name="picture_url",length=45,nullable = true)
    private String picture_url;

    @Column(name="phone_number")
    private Integer phoneNumber;

    @Column(name="address", length=45,nullable = true)
    private String address;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @OneToOne
    private User user;

    @OneToMany(mappedBy="profile")
    private List<Review> reviewList;


}
