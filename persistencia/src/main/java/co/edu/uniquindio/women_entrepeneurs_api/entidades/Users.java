package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="email", length=50,unique=true,nullable = false)
    private String email;

    @Column(name="name", length=45,nullable = false)
    private String name;
    @Column(name="password",length=45,nullable = false)
    private String password;

    @Column(name="dni",length=20,unique=true,nullable = false)
    private String dni;

    @Column(name="phone_number", length=15)
    private Integer phoneNumber;

    @Column(name="address", length=45,nullable = false)
    private String address;

    @Column(name="is_active",nullable = false)
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users user = (Users) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }


    @ManyToOne
    @NotNull
    private LevelAccess levelAccess;

}
