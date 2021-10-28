package prgrms.al.back.product.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByLocation(Location location);
}
