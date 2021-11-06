package prgrms.al.back.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.service.UserService;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService service;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private NearAreaStepOneRepository oneRepository;

    @BeforeEach
    @Transactional
    public void setTest(){
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

    @Test
    @DisplayName("createUserTest")
    public void createUser() throws Exception {
        //given
        UserDto userDto = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

        //when //then
        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-save",
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NULL).description("id"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("nickname"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("password"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("location"),
                                fieldWithPath("mannerTemperature").type(JsonFieldType.NUMBER).description("mannerTemperature"),
                                fieldWithPath("createdAt").type(JsonFieldType.NULL).description("createAt"),
                                fieldWithPath("attentions").type(JsonFieldType.NULL).description("attentions"),
                                fieldWithPath("products").type(JsonFieldType.NULL).description("products")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("nickname"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("password"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("location"),
                                fieldWithPath("mannerTemperature").type(JsonFieldType.NUMBER).description("mannerTemperature"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("createAt"),
                                fieldWithPath("attentions").type(JsonFieldType.NULL).description("attentions"),
                                fieldWithPath("products").type(JsonFieldType.NULL).description("products")
                        )));
    }

    @Test
    @DisplayName("readUserTest")
    public void readUser() throws Exception {
        //given
        UserDto userDto = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

        service.createUser(userDto);

        //when //then
        mockMvc.perform(get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-read",
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("nickname"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("password"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("location"),
                                fieldWithPath("mannerTemperature").type(JsonFieldType.NUMBER).description("mannerTemperature"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("createAt"),
                                fieldWithPath("attentions").type(JsonFieldType.NULL).description("attentions"),
                                fieldWithPath("products").type(JsonFieldType.NULL).description("products")
                        )));
    }

    @Test
    @DisplayName("updateUserTest")
    public void updateUser() throws Exception {
        //given
        UserDto userDto = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

        service.createUser(userDto);

        //when
        UserDto updateDto = UserDto.builder()
                .id(1L)
                .name("Sangsun")
                .nickname("update12")
                .password("upDated12!@")
                .location("seoul")
                .build();

        // then
        mockMvc.perform(post("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateDto))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-update",
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("nickname"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("password"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("location"),
                                fieldWithPath("mannerTemperature").type(JsonFieldType.NUMBER).description("mannerTemperature"),
                                fieldWithPath("createdAt").type(JsonFieldType.NULL).description("createAt"),
                                fieldWithPath("attentions").type(JsonFieldType.NULL).description("attentions"),
                                fieldWithPath("products").type(JsonFieldType.NULL).description("products")

                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("id"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("name"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING).description("nickname"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("password"),
                                fieldWithPath("location").type(JsonFieldType.STRING).description("location"),
                                fieldWithPath("mannerTemperature").type(JsonFieldType.NUMBER).description("mannerTemperature"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("createAt"),
                                fieldWithPath("attentions").type(JsonFieldType.NULL).description("attentions"),
                                fieldWithPath("products").type(JsonFieldType.NULL).description("products")
                        )));
    }

    @Test
    @DisplayName("deleteUserTest")
    public void deleteUser() throws Exception {
        //given
        UserDto userDto = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

        service.createUser(userDto);

        //when //then
        mockMvc.perform(delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-delete",
                        responseFields(
                               fieldWithPath("message").type(JsonFieldType.STRING).description("message")
                        )));
    }
}