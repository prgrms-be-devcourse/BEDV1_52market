package prgrms.al.back.attention.service;

import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.attention.domain.Attention;
import prgrms.al.back.attention.dto.AttentionSaveRequestDto;
import prgrms.al.back.attention.repository.AttentionRepository;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.repository.UserRepository;
import prgrms.al.back.user.service.UserService;

import java.util.List;

@Slf4j
@Service
@SpringBootTest
class AttentionServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    AttentionService attentionService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    NearAreaStepOneRepository oneRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AttentionRepository attentionRepository;

    @BeforeEach
    @Transactional
    public void setTest() {
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
    }

    @Transactional
    @Test
    public void test() throws NotFoundException, DuplicateMemberException {
        UserDto userDto = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

        userService.createUser(userDto);

        ProductRequest productRequest = ProductRequest.builder()
                .title("맥북 팝니다.")
                .content("싸게 드려요, 연락주세요")
                .price(1_000_000L)
                .nickname("soon12")
                .build();


//        productService.createProduct(productRequest);

        Long userId = userRepository.findByNickname(userDto.getNickname()).get().getId();
        Long productId = productRepository.findAll().get(0).getId();
        System.out.println(productId);


        attentionService.save(AttentionSaveRequestDto.builder()
                .productId(productId)
                .userId(userId)
                .build());


        System.out.println(productService.findById(productId).getTotalAttention());

        //Attention attention = attentionRepository.findById(1L).get();
        //System.out.println(attention.getProduct().getTitle());
        //attentionRepository.findAll();
        //List<Attention> list = attentionRepository.findAll();
        //System.out.println(list.get(0).getProduct().getTitle());
        // 수정 해야할것
        //List<ProductSearchResponse> attentionProducts = attentionService.getAttentionProducts(userId);
        //System.out.println(attentionProducts.get(0).getTitle());
    }
}