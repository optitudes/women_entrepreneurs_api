package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Profile;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmailAndIsActive(String email, boolean isActive);

    @Query("SELECT id FROM User  WHERE email = :email AND deletedAt is null AND isActive = true AND emailVerifiedAt is not null")
    Optional<Integer> findIdByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.profile.idNumber = :idNumber")
    Optional<User> findByIdNumber(@Param("idNumber") String idNumber);

    @Query("SELECT u.email FROM User u  INNER JOIN u.levelAccess la WHERE  la.accessCode=1001 AND u.deletedAt is null AND u.isActive = true AND u.emailVerifiedAt is not null")
    List<String> findAdminsEmail();


   // @Query("SELECT u From User u WHERE u.profile.idNumber = :idNumber AND u.password = :password")
    // Optional<User> findByidNumberAndPassword(@Param("idNumber")String idNumber,@Param("password") String password);




}