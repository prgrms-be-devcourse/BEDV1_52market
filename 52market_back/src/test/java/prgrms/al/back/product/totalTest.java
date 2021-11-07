package prgrms.al.back.product;

import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;
import prgrms.al.back.product.convertor.ProductConvertor;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.repository.UserRepository;
import prgrms.al.back.user.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class totalTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductConvertor productConvertor;

    @Autowired
    ProductService productService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    NearAreaStepOneRepository oneRepository;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("상품을 잘 등록하는지, 이미지 잘저장하는지")
    @Rollback(value = false)
    void createProduct() throws DuplicateMemberException {
        Location location1 = Location.builder()
                .name("incheon")
                .n(12.0)
                .e(24.0)
                .build();

        Location location2 = Location.builder()
                .name("seoul")
                .n(20.0)
                .e(30.0)
                .build();

        locationRepository.save(location1);
        locationRepository.save(location2);

        NearAreaStepOne one = NearAreaStepOne.builder()
                .start(location1)
                .end(location2)
                .build();

        oneRepository.save(one);

        UserDto userDto = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

        userService.createUser(userDto);

        List<String> urls = new ArrayList<>();
        urls.add("123123123");
        urls.add("23234234234");

        ProductRequest productRequest = ProductRequest.builder()
                .title("맥북 팝니다.")
                .content("싸게 드려요, 연락주세요")
                .price(1_000_000L)
                .nickname("soon12")
                .urlList(urls)
                .build();

        productService.createProduct(productRequest);

    }

}
