package prgrms.al.back.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.user.convertor.UserConvertor;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.domain.UserDto;
import prgrms.al.back.user.repository.UserRepository;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private UserConvertor userConvertor;

    @Override
    public UserDto findById(Long id) {
        User user = repository.findById(id).get();
        return userConvertor.toDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userConvertor.of(userDto);
        repository.save(user);
        return userConvertor.toDto(repository.findByNickName(userDto.getNickName()).get());
    }

    @Override
    public UserDto updatedUser(UserDto userDto) {
        User user = repository.findById(userDto.getUserId()).get();
        user.updateInfo(userDto.getName(), userDto.getNickName(), userDto.getPassword(), userDto.getLocation());
        return userConvertor.toDto(repository.findById(user.getUserId()).get());
    }

    @Override
    public String deleteUser(Long id) {
        repository.deleteById(id);
        return "Deleted User";
    }

}
