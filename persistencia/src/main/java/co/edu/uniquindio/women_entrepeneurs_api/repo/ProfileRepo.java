package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Profile;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByIdNumber(String idNumber);
    Optional<Profile> findProfileByUser(User u);
    Page<Profile> findAll(Pageable pageable);
}
