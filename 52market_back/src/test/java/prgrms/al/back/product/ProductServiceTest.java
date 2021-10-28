package prgrms.al.back.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.repository.UserRepository;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    ProductRepository productRepository;

    ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository, userRepository);
    }

    @Test
    @DisplayName("상품을 잘 등록하는지")
    void createProduct() {
        // TODO: 2021/10/26 어떻게 검증해야 할까요??

        when(userRepository.findByNickName("tester")).thenReturn((Optional.of(User.builder()
                .nickName("tester")
                .build())));

        ProductRequest productRequest = ProductRequest.builder()
            .title("맥북 팝니다.")
            .content("싸게 드려요, 연락주세요")
            .price(1_000_000L)
            .nickName("tester")
            .build();

        productService.createProduct(productRequest);
    }

    @Test
    @DisplayName("Location을 기준으로 상품을 쿼리한다.")
    void findProductsByLocation() {
        // given
        Location imun = new Location("이문동", 120.0, 130.0);
        Product product1 = new Product("상품1", "상품1 팝니다~~", 1_000_000L, imun);
        Product product2 = new Product("상품2", "상품2 팝니다~~", 2_000_000L, imun);
        Product product3 = new Product("상품3", "상품3 팝니다~~", 3_000_000L, imun);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        when(productRepository.findByLocation(imun)).thenReturn(products);

        // when
        List<ProductSearchResponse> result = productService.findProductsByLocation(
            imun);

        // then
        assertThat(result.size()).isEqualTo(3);
    }
}
