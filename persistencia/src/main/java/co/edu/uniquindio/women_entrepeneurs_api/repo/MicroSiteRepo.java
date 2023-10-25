package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.MicroSite;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MicroSiteRepo extends JpaRepository<MicroSite, Integer> {

    Page<MicroSite> findAll(Pageable pageable);

    Optional<MicroSite> findById(Integer id);
    Optional<MicroSite> findByName(String name);

    List<MicroSite> findLastSixByIsActiveTrue();

}
