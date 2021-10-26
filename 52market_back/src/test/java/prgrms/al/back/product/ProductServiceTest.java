package prgrms.al.back.product;

import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.User;
import prgrms.al.back.user.UserRepository;

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

        when(userRepository.findByNickName("tester")).thenReturn((Optional.of(new User("tester"))));

        ProductRequest productRequest = ProductRequest.builder()
            .title("맥북 팝니다.")
            .content("싸게 드려요, 연락주세요")
            .price(1_000_000L)
            .nickName("tester")
            .build();

        productService.createProduct(productRequest);
    }
}
