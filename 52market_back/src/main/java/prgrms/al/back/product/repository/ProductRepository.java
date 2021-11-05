package prgrms.al.back.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByLocation(Location location);

    @Query("select p from product p where p.location = :location and p.title like %:keyword%")
    List<Product> findByLocationAndTitleContaining(@Param("location") Location location, @Param("keyword") String keyword);
}
