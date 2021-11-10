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

    @Query("SELECT p FROM attention a JOIN a.product p WHERE a.user.id= :userId")
    List<Product> findAttentionProducts(@Param("userId") Long userId);
}
// 쿼리의 네이티브 옵션을 트루로 줘야한다. 사용할때 실패했는지 안했는지 알수있다.