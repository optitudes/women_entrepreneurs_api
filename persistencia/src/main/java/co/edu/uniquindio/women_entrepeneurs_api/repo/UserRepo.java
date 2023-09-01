package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Bill;
import co.edu.uniquindio.women_entrepeneurs_api.entidades.Product;
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

    @Query("select p from Users u  JOIN u.favorite.productList p where u.email = :email")
    List<Product> obtenerProductosFavoritosUser(String email);
    @Query("select p from Users u  JOIN u.shoppingCart.productList p where u.email = :email")
    List<Product> obtenerProductosCarritoUser(String email);

    @Query("select u.email, c from Users u join u.commentList c")
    List<Object[]> listarUsuariosYComentarios();

    @Query("select u.name, c.description from Users u join u.couponList c")
    List<String[]> listarUsuariosYCupones();

    @Query("select u.name, c.comment from Users u LEFT JOIN u.commentList c")
    List<String[]> listarNombresUsuariosYComentarios();

    @Query("select u from Users u where u.name like %:patron%")
    List<Users> buscarPatronNombre(String patron);

    @Query("SELECT  b.billCode, b.total, bd.amount, bd.price FROM Bill b JOIN b.billDetailList bd WHERE b.user.id = :userId")
    List<Object[]> getBIllsAndBillDetails(Integer userId);

    @Query("SELECT  u.billList FROM Users  u  WHERE u.email = :email")
    List<Bill> getBills(String email);



}