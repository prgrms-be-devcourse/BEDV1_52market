package prgrms.al.back.user.service;

import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import prgrms.al.back.location.domain.Location;
import prgrms.al.back.location.service.LocationService;
import prgrms.al.back.user.convertor.UserConvertor;
import prgrms.al.back.user.domain.User;
import prgrms.al.back.user.dto.UserDeleteResponse;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.exception.ErrorCode;
import prgrms.al.back.user.repository.UserRepository;

import javax.validation.ConstraintViolationException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository repository;
    private UserConvertor userConvertor;
    private LocationService locationService;

    @Transactional(readOnly = true)
    @Override
    public UserDto findById(Long id) throws NotFoundException{
        return repository.findById(id)
                .map(user -> userConvertor.toDto(user))
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_USER.toString()));
    }

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) throws DuplicateMemberException, ConstraintViolationException {
        if (repository.existsByName(userDto.getName())){
            throw new DuplicateMemberException(ErrorCode.NAME_DUPLICATION.toString());
        }

        if (repository.existsByNickname(userDto.getNickname())){
            throw new DuplicateMemberException(ErrorCode.NICKNAME_DUPLICATION.toString());
        }

        User user = userConvertor.of(userDto);
        user.updateLocationInfo(locationService.findByName(userDto.getLocation()).get());
        repository.save(user);

        return userConvertor.toDto(repository.findByNickname(userDto.getNickname()).get());
    }

    @Transactional
    @Override
    public UserDto updatedUser(UserDto userDto) throws NotFoundException {
        if (!repository.existsById(userDto.getId())){
            throw new NotFoundException(ErrorCode.NOT_FOUND_USER.toString());
        }
        User user = repository.findById(userDto.getId()).get();
        Location location = locationService.findByName(userDto.getLocation()).get();
        user.updateInfo(userDto.getName(), userDto.getNickname(), userDto.getPassword(), location);
        return userConvertor.toDto(repository.findById(user.getId()).get());
    }

    @Transactional
    @Override
    public UserDeleteResponse deleteUser(Long id) throws NotFoundException{

        if (!repository.existsById(id)){
            throw new NotFoundException(ErrorCode.NOT_FOUND_USER.toString());
        }

        repository.deleteById(id);
        return UserDeleteResponse.builder().message("success").build();
    }
}
