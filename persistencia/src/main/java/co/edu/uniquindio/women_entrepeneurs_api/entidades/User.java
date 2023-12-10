package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="email", length=50,unique=true,nullable = false)
    private String email;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="is_active",nullable = false)
    private Boolean isActive;

    @Column(name="email_verified_at")
    private LocalDateTime emailVerifiedAt;

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

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @ManyToOne
    @NotNull
    private LevelAccess levelAccess;

    @OneToOne(mappedBy="user")
    private Profile profile;

    @OneToOne(mappedBy="user")
    private Venture venture;





}
