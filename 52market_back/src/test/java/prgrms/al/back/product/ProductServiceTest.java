package prgrms.al.back.product;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import prgrms.al.back.product.convertor.ProductConvertor;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.repository.UserRepository;
import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    @Autowired
    UserRepository userRepository;

    @MockBean
    @Autowired
    ProductRepository productRepository;

    @MockBean
    @Autowired
    ProductConvertor productConvertor;

    @Autowired
    ProductService productService;

    @Test
    @DisplayName("상품을 잘 등록하는지")
    void createProduct() {
        // TODO: 2021/10/26 어떻게 검증해야 할까요??
        List<Product> list = new ArrayList<>();
        when(userRepository.findByNickname("tester")).thenReturn((Optional.of(User.builder()
                .nickname("tester")
                .build())));

        ProductRequest productRequest = ProductRequest.builder()
                .title("맥북 팝니다.")
                .content("싸게 드려요, 연락주세요")
                .price(1_000_000L)
                .nickname("tester")
                .build();

        productService.createProduct(productRequest);
    }
}
