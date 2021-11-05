package prgrms.al.back.user.convertor;

import org.springframework.stereotype.Component;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.dto.UserFindResponse;
import prgrms.al.back.user.dto.UserSignupResponse;

@Component
public class UserConvertor {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .location(user.getLocation().getName())
                .mannerTemperature(user.getMannerTemperature())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User of(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .nickname(userDto.getNickname())
                .password(userDto.getPassword())
                .build();
    }

    public UserFindResponse toFindResponse(User user){
        return UserFindResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .location(user.getLocation().getName())
                .mannerTemperature(user.getMannerTemperature())
                .createdAt(user.getCreatedAt())
                .attentions(user.getAttentions())
                .products(user.getProducts())
                .sendLetters(user.getSendLetters())
                .receiveLetters(user.getReceiveLetters())
                .build();
    }

    public UserSignupResponse toSignResponse(User user){
        return UserSignupResponse.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .location(user.getLocation().getName())
                .mannerTemperature(user.getMannerTemperature())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
