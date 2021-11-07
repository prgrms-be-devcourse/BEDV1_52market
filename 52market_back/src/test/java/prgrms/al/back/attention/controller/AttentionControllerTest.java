package prgrms.al.back.attention.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import prgrms.al.back.attention.dto.AttentionSaveRequestDto;
import prgrms.al.back.attention.repository.AttentionRepository;
import prgrms.al.back.attention.service.AttentionService;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;
import prgrms.al.back.product.dto.ProductRequest;
import prgrms.al.back.product.repository.ProductRepository;
import prgrms.al.back.product.service.ProductService;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.repository.UserRepository;
import prgrms.al.back.user.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
@Configurable
class AttentionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AttentionService attentionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AttentionRepository attentionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    NearAreaStepOneRepository oneRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;


    Long userId;
    Long productId;

    @BeforeEach
    void setUp() throws DuplicateMemberException {
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

        userId = userRepository.findByNickname(userDto.getNickname()).get().getId();
        productId = productRepository.findAll().get(0).getId();


    }

    @Test
    void saveAttentionTest() throws Exception {
        AttentionSaveRequestDto attentionSaveRequestDto = AttentionSaveRequestDto.builder()
                .userId(userId)
                .productId(productId)
                .build();

        mockMvc.perform(post("/api/attention")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attentionSaveRequestDto)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("attention-save",
                        requestFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("userId"),
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("productId")
                        ),
                        responseFields(
                                fieldWithPath("statusCode").type(JsonFieldType.NUMBER).description("statusCode"),
                                //fieldWithPath("data").type(JsonFieldType.NUMBER).description("data"),
                                fieldWithPath("serverDateTime").type(JsonFieldType.STRING).description("serverDateTime")
                        )));

    }


}