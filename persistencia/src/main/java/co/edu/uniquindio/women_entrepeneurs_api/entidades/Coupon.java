package co.edu.uniquindio.women_entrepeneurs_api.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Coupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="coupon_value",nullable = false)
    private Double couponValue;

    @Column(name="security_code", length=20,unique=true,nullable = false)
    private String securityCode;

    @Column(name="description", length=100)
    private String description;

    @Column(name="is_active",nullable = false)
    private Boolean isActive;

    @Column(name="limit_date",nullable = false)
    private Date limitDate;

    @ManyToOne
    private Users user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coupon coupon = (Coupon) o;

        return Objects.equals(id, coupon.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
