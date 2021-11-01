package prgrms.al.back.attention.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.product.domain.Product;

import java.util.Optional;
import java.util.List;

@Repository
public interface AttentionRepository extends JpaRepository<Attention, Long> {

    @Query("SELECT product FROM attention as a WHERE a.user.id=:userId")
    List<Product> findAttentionProducts(@Param("userId") Long userId);
}
