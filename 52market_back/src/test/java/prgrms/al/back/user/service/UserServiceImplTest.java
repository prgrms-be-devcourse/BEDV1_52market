package prgrms.al.back.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import prgrms.al.back.user.domain.Location;
import prgrms.al.back.user.dto.UserDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    UserDto userDto;

    @BeforeEach
    private void setTest(){
        userDto = UserDto.builder()
                .name("Sangsun")
                .nickName("soon")
                .password("testpass")
                .location(new Location("incheon"))
                .build();
    }

    @Test
    @DisplayName("유저 생성")
    public void createTest(){
        //given
        UserDto createdUserDto = service.createUser(userDto);

        //when
        UserDto findEntity = service.findById(createdUserDto.getUserId());

        //then
        assertThat(findEntity.getNickName(), is(createdUserDto.getNickName()));
    }

    @Test
    @DisplayName("유저 업데이트")
    public void updateTest(){
        //given
        UserDto createdUserDto = service.createUser(userDto);
        UserDto forUpdateDto = UserDto.builder()
                .userId(createdUserDto.getUserId())
                .name("update")
                .nickName("updated")
                .build();

        //when
        UserDto updatedDto = service.updatedUser(forUpdateDto);

        //then
        assertAll(
                () -> assertThat(updatedDto.getName(), is(forUpdateDto.getName())),
                () -> assertThat(updatedDto.getNickName(), is(forUpdateDto.getNickName())),
                () -> assertThat(updatedDto.getLocation(), is("Incheon"))
        );
    }
}