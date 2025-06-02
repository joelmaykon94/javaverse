package data.product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
    Long countAllByCategoryId(Long categoryId);
    @Query("select p from Product p JOIN p.reviews r WHERE r.id = ?1")
    Product findProductByReviewId(Long reviewId);
    void deleteAllByCategoryId(Long id);
    List<Product> findAllByCategoryId(Long id);
}