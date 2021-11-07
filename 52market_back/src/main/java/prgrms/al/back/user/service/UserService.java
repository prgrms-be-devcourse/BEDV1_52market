package prgrms.al.back.user.service;

import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import prgrms.al.back.user.dto.UserDeleteResponse;
import prgrms.al.back.user.dto.UserDto;

public interface UserService {

    public UserDto findById(Long id) throws NotFoundException;

    public UserDto createUser(UserDto userDto) throws DuplicateMemberException;

    public UserDto updatedUser(UserDto userDto) throws NotFoundException;

    public UserDeleteResponse deleteUser(Long id) throws NotFoundException;
}
