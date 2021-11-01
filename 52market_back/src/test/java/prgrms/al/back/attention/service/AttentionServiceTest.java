package prgrms.al.back.attention.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.attention.dto.AttentionSaveRequestDto;
import prgrms.al.back.product.domain.Product;
import prgrms.al.back.product.dto.ProductSearchResponse;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.user.domain.Location;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Service
@SpringBootTest
class AttentionServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AttentionService attentionService;

    @Transactional
    @Test
    public void test(){
        User user = User.builder()
                .name("minkyu")
                .nickName("minyu")
                .password("testpass")
                .location(new Location("incheon"))
                .build();
        userRepository.save(user);

        Product product = Product.builder()
                .title("오징어 팝니다")
                .content("100만원 연락주세요")
                .price(1_000_000L)
                .location(new Location("incheon"))
                .totalAttention(0)
                .build();

        product.setOwner(user);
        product.setCreatedBy(user);
        productRepository.save(product);

        attentionService.save(AttentionSaveRequestDto.builder()
                        .productId(product.getId())
                        .userId(user.getUserId())
                .build());

        List<ProductSearchResponse> attentionProducts = attentionService.getAttentionProducts(user.getUserId());
        System.out.println(attentionProducts.get(0).getTitle());
    }
}