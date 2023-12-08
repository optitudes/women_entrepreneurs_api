package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitude;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitudeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MicroSiteSolicitudeRepo extends JpaRepository<MicroSiteSolicitude, Long> {

    Optional<MicroSiteSolicitude> findMicroSiteSolicitudeById(Long id);

    @Query("SELECT microSoli FROM MicroSiteSolicitude microSoli WHERE (:status is null or microSoli.status = :status) AND microSoli.deletedAt is null")
    List<MicroSiteSolicitude> obtainWithFilters(@Param("status") MicroSiteSolicitudeStatus status);

    Page<MicroSiteSolicitude> findAll(Pageable pageable);
}
