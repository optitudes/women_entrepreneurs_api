package co.edu.uniquindio.women_entrepeneurs_api.repo;

import co.edu.uniquindio.women_entrepeneurs_api.entidades.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProductRepo extends JpaRepository<Product, Integer> {

    Optional<Product> findByName(String name);
    Optional<Product> findByIsAvailable(Boolean isAvailable);
    List<Product> findByIsActive(Boolean isActive);
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Integer id);

    @Query("select distinct p from Product p where p.name like %:pattern% OR CAST(p.price AS string) like %:pattern%")
    List<Product> finfByPattern(String pattern);



}
