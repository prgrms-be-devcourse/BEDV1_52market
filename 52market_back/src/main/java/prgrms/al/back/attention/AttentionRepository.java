package prgrms.al.back.attention;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prgrms.al.back.product.Product;

import java.util.Optional;
import java.util.List;

@Repository
public interface AttentionRepository extends JpaRepository<Attention, Long> {
    @Override
    Optional<Attention> findById(Long attentionId);

    @Query("SELECT p FROM attention as a JOIN a.product as p WHERE a.user.user_id=:user_id")
    List<Product> findAttentionProducts(@Param("user_id") Long userId);
}
