package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.BillDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillDetailRepo extends JpaRepository<BillDetail, Integer> {

    Optional<BillDetail> findByPrice(String price);

    Page<BillDetail> findAll(Pageable pageable);

    Optional<BillDetail> findById(Integer id);

    Optional<BillDetail> findByAmount(Integer amount);

    @Query("SELECT  bd FROM BillDetail bd  WHERE bd.bill.id = :billId")
    List<BillDetail> getDetailsByBillId(Integer billId);

}