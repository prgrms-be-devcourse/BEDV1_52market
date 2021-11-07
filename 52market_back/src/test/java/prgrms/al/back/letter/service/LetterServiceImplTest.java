package prgrms.al.back.letter.service;

import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.letter.dto.LetterCreateRequest;
import prgrms.al.back.letter.repository.LetterRepository;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@SpringBootTest
class LetterServiceImplTest {

    @Autowired
    LetterService service;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    NearAreaStepOneRepository oneRepository;

    LetterCreateRequest letterCreateRequest;

    @BeforeEach
    @Transactional
    public void setTest() throws DuplicateMemberException {

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

        UserDto sender = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

        UserDto receiver = UserDto.builder()
                .name("Sangsun2")
                .nickname("soon34")
                .password("teSt@40^$")
                .location("incheon")
                .build();

        UserDto senderEntity = userService.createUser(sender);
        UserDto receiverEntity = userService.createUser(receiver);

        List<String> urls = new ArrayList<>();
        urls.add("123123123");
        urls.add("23234234234");

        ProductRequest productRequest = new ProductRequest(
                "맥북 팝니다",
                "싸게 드려요, 연락주세요",
                1000L,
                sender.getNickname(),urls);

        productService.createProduct(productRequest);

        letterCreateRequest = LetterCreateRequest.builder()
                .body("testBody")
                .productId(1L)
                .senderId(senderEntity.getId())
                .receiverId(receiverEntity.getId())
                .build();
    }

    @Test
    @DisplayName("쪽지 생성")
    public void createLetter(){
        //given
        var createEntity = service.createLetter(letterCreateRequest);

        //when
        var findEntity = service.findAllByUserId(createEntity.getSenderId()).get(0);

        //then
        assertThat(createEntity.getBody(), is(findEntity.getBody()));
    }

    @Test
    @DisplayName("쪽지 삭제")
    public void deleteLetter() throws NotFoundException {
        //given
        var createEntity = service.createLetter(letterCreateRequest);
        assertThat(createEntity.getBody(), is("testBody"));

        //when
        var entity = service.deleteLetter(createEntity.getId());

        //then
        assertThat(entity.getMessage(), is("success"));
    }
}