package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSiteSolicitude;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MicroSiteSolicitudeRepo extends JpaRepository<MicroSiteSolicitude, Long> {

    Optional<MicroSiteSolicitude> findMicroSiteSolicitudeById(Long id);
    Page<MicroSiteSolicitude> findAll(Pageable pageable);
}
