package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

    Optional<Users> findByEmailAndPassword(String email, String password);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByDni(String dni);

    Page<Users> findAll(Pageable pageable);

    Optional<Users> findByEmailAndIsActive(String email, boolean isActive);

    @Query("select u from Users u where u.name like %:patron%")
    List<Users> buscarPatronNombre(String patron);



}