package prgrms.al.back.product.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.domain.Product;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    @DisplayName("위치로 상품을 조회한다.")
    void findProductsByLocation() {
        //given
        Location songpa = entityManager.persistFlushFind(new Location("송파구", 10.0, 20.0));
        Location imun = entityManager.persistFlushFind(new Location("이문동", 120.0, 130.0));
        Product product1 = entityManager.persistFlushFind(new Product("상품1", "상품1 팝니다~~", 1_000_000L, imun));
        Product product2 = entityManager.persistFlushFind(new Product("상품2", "상품2 팝니다~~", 2_000_000L, imun));
        Product product3 = entityManager.persistFlushFind(new Product("상품3", "상품3 팝니다~~", 3_000_000L, imun));
        entityManager.persistFlushFind(new Product("상품4", "상품4 팝니다~~", 3_000_000L, songpa));

        // when
        List<Product> findProducts = productRepository.findByLocation(imun);

        // then
        assertThat(findProducts).contains(product1, product2, product3);
    }

    @Test
    @DisplayName("위치로 상품을 조회한다. + 상품 제목에 검색어가 포함된 상품을 조회한다.")
    void findProductsByTitleContaining() {
        // given
        Location imun = entityManager.persistFlushFind(new Location("이문동", 120.0, 130.0));
        Location hoegi = entityManager.persistFlushFind(new Location("회기동", 120.0, 130.0));
        entityManager.persistFlushFind(new Product("이문동에서 맥북 팔아요", "호호호", 1_000_000L, imun));
        entityManager.persistFlushFind(new Product("이문동에서 치킨 팔아요", "쿄쿄쿄", 1_000_000L, imun));
        entityManager.persistFlushFind(new Product("회기에서 치킨 팔아요", "쿄쿄쿄", 1_000_000L, hoegi));
        Product product = entityManager.persistFlushFind(new Product("회기에서 맥북 팔아요", "쿄쿄쿄", 1_000_000L, hoegi));

        // when
        List<Product> findProducts = productRepository.findByLocationAndTitleContaining(hoegi, "맥북");


        // then
        assertThat(findProducts).contains(product);
    }

    @Test
    @DisplayName("조회한 위치에 등록된 상품이 없는 경우 빈 리스트를 반환한다")
    void findProductsByLocation_withNoResult() {
        Location imun = entityManager.persistFlushFind(new Location("이문동", 120.0, 130.0));
        List<Product> result = productRepository.findByLocation(imun);
        assertThat(result.size()).isEqualTo(0);
    }
}
