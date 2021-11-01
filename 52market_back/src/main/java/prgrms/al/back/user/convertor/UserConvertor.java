package prgrms.al.back.user.convertor;

import org.springframework.stereotype.Component;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.UserDto;

@Component
public class UserConvertor {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
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
                .nickname(userDto.getNickname())
                .password(userDto.getPassword())
                .build();
    }
}
