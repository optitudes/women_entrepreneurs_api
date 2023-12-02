package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "micro_site_solicitude")
public class MicroSiteSolicitude implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="status")
    @Enumerated(value = EnumType.STRING)
    private MicroSiteSolicitudeStatus status;

    @Column(name="comment")
    private String comment;



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

        MicroSiteSolicitude user = (MicroSiteSolicitude) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @OneToOne
    private Profile user;
    @OneToOne
    private Profile admin;
    @OneToOne
    private Venture venture;
    @OneToOne
    private MicroSite microSite;

}
