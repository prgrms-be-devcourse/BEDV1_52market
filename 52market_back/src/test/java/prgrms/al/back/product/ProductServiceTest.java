package prgrms.al.back.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.product.convertor.ProductConvertor;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.domain.ProductStatus;
import prgrms.al.back.product.dto.ProductCreateRequest;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.SellerDto;
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

        SellerDto seller = new SellerDto("tester", "tester_nickname", "이문동", 36.5);

        ProductCreateRequest request = ProductCreateRequest.builder()
            .title("test title")
            .content("test content")
            .price(100_000L)
            .location(seller.getLocation())
            .seller(seller)
            .imageUrls(Arrays.asList("sample.image.url1", "sample.image.url2"))
            .build();

        productService.createProduct(request);
    }

    @Test
    @DisplayName("Location을 기준으로 상품을 쿼리한다.")
    void findProductsByLocation() {
        // given
        Location imun = new Location("이문동", 120.0, 130.0);
        Product product1 = new Product("상품1", "상품1 팝니다~~", 1_000_000L, imun);
        Product product2 = new Product("상품2", "상품2 팝니다~~", 2_000_000L, imun);
        Product product3 = new Product("상품3", "상품3 팝니다~~", 3_000_000L, imun);

        when(productRepository.findByLocation(imun)).thenReturn(
            Arrays.asList(product1, product2, product3));

        // when
        List<ProductSearchResponse> result = productService.findByLocation(imun);

        // then
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("상품을 예약 상태로 변경한다")
    void reserve() {
        // given
        Location imun = new Location("이문동", 120.0, 130.0);
        Product product = new Product("상품1", "상품1 팝니다~~", 1_000_000L, imun);

        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.SELLING);
        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        // when
        productService.reserve(any());

        // then
        assertThat(product.getProductStatus()).isEqualTo(ProductStatus.RESERVED);
    }
}
