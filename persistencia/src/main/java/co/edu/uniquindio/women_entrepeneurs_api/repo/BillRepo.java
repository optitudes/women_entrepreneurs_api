package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Bill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer> {

    Optional<Bill> findByBillCode(String billCode);

    Page<Bill> findAll(Pageable pageable);

    Optional<Bill> findById(Integer id);

    Optional<Bill> findByTotal(Integer total);

}