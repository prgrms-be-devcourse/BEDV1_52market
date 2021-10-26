package prgrms.al.back.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prgrms.al.back.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
