package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProfileRepo extends JpaRepository<Profile, Integer> {

    Optional<Profile> findByIdNumber(String idNumber);



}
