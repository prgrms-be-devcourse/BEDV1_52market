package prgrms.al.back.user.service;

import prgrms.al.back.user.domain.UserDto;

public interface UserService {

    public UserDto findById(Long id);

    public UserDto createUser(UserDto userDto);

    public UserDto updatedUser(UserDto userDto);

    public String deleteUser(Long id);
}
