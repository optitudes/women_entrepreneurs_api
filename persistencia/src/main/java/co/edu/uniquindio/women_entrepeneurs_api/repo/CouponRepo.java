package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository

public interface CouponRepo  extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findBySecurityCode(String securityCode);

    Optional<Coupon> findBySecurityCodeAndLimitDateAndIsActive(String securityCode, Date limitDate,Boolean isActive);

    Page<Coupon> findAll(Pageable pageable);

    Optional<Coupon> findById(Integer id);

}