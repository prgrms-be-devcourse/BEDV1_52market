package prgrms.al.back.letter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.letter.dto.LetterCreateRequest;
import prgrms.al.back.letter.service.LetterService;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
class LetterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LetterService service;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private NearAreaStepOneRepository oneRepository;

    UserDto senderEntity;
    UserDto receiverEntity;

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

        senderEntity = userService.createUser(sender);
        receiverEntity = userService.createUser(receiver);

        List<String> urls = new ArrayList<>();
        urls.add("123123123");
        urls.add("23234234234");

        ProductRequest productRequest = new ProductRequest(
                "맥북 팝니다",
                "싸게 드려요, 연락주세요",
                1000L,
                sender.getNickname(),urls);


        productService.createProduct(productRequest);
    }

    @Test
    @DisplayName("createLetterTest")
    public void createLetter() throws Exception {
        //given
        LetterCreateRequest letterCreateRequest = LetterCreateRequest.builder()
                .body("testBody")
                .productId(1L)
                .senderId(senderEntity.getId())
                .receiverId(receiverEntity.getId())
                .build();

        //when //then
        mockMvc.perform(post("/api/letters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(letterCreateRequest))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("letter-save",
                        requestFields(
                                fieldWithPath("body").type(JsonFieldType.STRING).description("body"),
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("productId"),
                                fieldWithPath("senderId").type(JsonFieldType.NUMBER).description("senderId"),
                                fieldWithPath("receiverId").type(JsonFieldType.NUMBER).description("receiverId")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("body").type(JsonFieldType.STRING).description("body"),
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("productId"),
                                fieldWithPath("senderId").type(JsonFieldType.NUMBER).description("senderId"),
                                fieldWithPath("receiverId").type(JsonFieldType.NUMBER).description("receiverId")
                        )));
    }


    @Test
    @DisplayName("deleteLetterTest")
    public void deleteLetter() throws Exception {
        //given
        LetterCreateRequest letterCreateRequest = LetterCreateRequest.builder()
                .body("testBody")
                .productId(1L)
                .senderId(senderEntity.getId())
                .receiverId(receiverEntity.getId())
                .build();
        service.createLetter(letterCreateRequest);

        //when //then
        mockMvc.perform(delete("/api/letters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("letter-delete",
                        responseFields(
                                fieldWithPath("message").type(JsonFieldType.STRING).description("message")
                        )));
    }


    @Test
    @DisplayName("readLetterTest")
    public void readLetter() throws Exception {
        //given
        LetterCreateRequest letterCreateRequest = LetterCreateRequest.builder()
                .body("testBody")
                .productId(1L)
                .senderId(senderEntity.getId())
                .receiverId(receiverEntity.getId())
                .build();
        service.createLetter(letterCreateRequest);

        //when //then
        mockMvc.perform(get("/api/letters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("letter-read",
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("[].body").type(JsonFieldType.STRING).description("body"),
                                fieldWithPath("[].productId").type(JsonFieldType.NUMBER).description("productId"),
                                fieldWithPath("[].senderId").type(JsonFieldType.NUMBER).description("senderId"),
                                fieldWithPath("[].receiverId").type(JsonFieldType.NUMBER).description("receiverId")
                        )));
    }

}