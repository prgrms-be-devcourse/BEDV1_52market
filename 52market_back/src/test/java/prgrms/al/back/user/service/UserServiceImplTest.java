package prgrms.al.back.user.service;

import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.domain.NearAreaStepOne;
import prgrms.al.back.location.repository.LocationRepository;
import prgrms.al.back.location.repository.NearAreaStepOneRepository;
import prgrms.al.back.user.dto.UserDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private NearAreaStepOneRepository oneRepository;

    UserDto userDto;

    @BeforeEach
    @Transactional
    public void setTest(){
        userDto = UserDto.builder()
                .name("Sangsun")
                .nickname("soon12")
                .password("teSt13!@45")
                .location("incheon")
                .build();

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
    @DisplayName("유저 생성")
    public void createTest() throws DuplicateMemberException, NotFoundException {
        //given
        UserDto createdUserDto = service.createUser(userDto);

        //when
        UserDto findEntity = service.findById(createdUserDto.getId());

        //then
        assertThat(findEntity.getNickname(), is(createdUserDto.getNickname()));
    }

    @Test
    @DisplayName("유저 생성 실패")
    public void failCreateTest() throws DuplicateMemberException, NotFoundException{
        //given
        UserDto failUserDto = UserDto.builder()
                .name("Sa")
                .nickname("fai")
                .password("failpassword")
                .location("incheon")
                .build();

        //when
        var entity = service.createUser(failUserDto);

        //then
        assertThat(entity, is(new DuplicateMemberException("")));
    }

    @Test
    @DisplayName("유저 업데이트")
    public void updateTest() throws DuplicateMemberException, NotFoundException {
        //given
        UserDto createdUserDto = service.createUser(userDto);
        UserDto forUpdateDto = UserDto.builder()
                .id(createdUserDto.getId())
                .name("Sangsun")
                .nickname("update12")
                .password("upDated12!@")
                .location("incheon")
                .build();

        //when
        UserDto updatedDto = service.updatedUser(forUpdateDto);

        //then
        assertAll(
                () -> assertThat(updatedDto.getName(), is(forUpdateDto.getName())),
                () -> assertThat(updatedDto.getNickname(), is(forUpdateDto.getNickname())),
                () -> assertThat(updatedDto.getLocation(), is("incheon"))
        );
    }
}