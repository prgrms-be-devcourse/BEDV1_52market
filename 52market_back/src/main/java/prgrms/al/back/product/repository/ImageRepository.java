package prgrms.al.back.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prgrms.al.back.product.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
