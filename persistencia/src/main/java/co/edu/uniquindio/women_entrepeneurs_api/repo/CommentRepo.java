package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Comment;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface CommentRepo  extends JpaRepository<Comment, Integer> {

    Optional<Comment> findByUser(Users user);
    Optional<Comment> findByComment(String comment);
    Page<Comment> findAll(Pageable pageable);

    Optional<Comment> findById(Integer id);

    @Query("select c.product.name from Comment c where c.id = :id")
    String obtenerNombreProducto(Integer id);

    @Query("select c.user.name from Comment c where c.id = :id")
    String obtenerNombreUser(Integer id);
}
