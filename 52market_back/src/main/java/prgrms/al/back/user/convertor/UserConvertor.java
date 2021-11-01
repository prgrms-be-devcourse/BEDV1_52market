package prgrms.al.back.user.convertor;

import org.springframework.stereotype.Component;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class UserConvertor {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .nickName(user.getNickName())
                .password(user.getPassword())
                .location(user.getLocation().getName())
                .mannerTemperature(user.getMannerTemperature())
                .createdAt(user.getCreatedAt())
                .attentions(user.getAttentions())
                .products(user.getProducts())
                .build();
    }

    public User of(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .nickName(userDto.getNickName())
                .password(userDto.getPassword())
                .build();
    }
}
