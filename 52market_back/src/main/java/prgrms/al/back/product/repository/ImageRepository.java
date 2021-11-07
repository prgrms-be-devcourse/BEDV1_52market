package prgrms.al.back.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prgrms.al.back.product.domain.Image;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT img FROM image AS img WHERE img.product.id=:productId ")
    List<Image> findImagesByProductId(@Param("productId") Long productId);
}
