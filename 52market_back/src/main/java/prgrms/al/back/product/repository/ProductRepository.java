package prgrms.al.back.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.user.domain.User;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
