package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.LevelAccess;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Venture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface VentureRepo extends JpaRepository<Venture, Integer> {

    Page<Venture> findAll(Pageable pageable);

    Optional<Venture> findById(Integer id);
    Optional<Venture> findByName(String name);

}
