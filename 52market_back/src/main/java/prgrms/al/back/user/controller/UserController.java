package prgrms.al.back.user.controller;

import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.service.UserServiceImpl;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws NotFoundException{
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws NotFoundException{
        return ResponseEntity.ok(service.deleteUser(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(final @RequestBody @Valid UserDto userDto) throws DuplicateMemberException {
        return ResponseEntity.ok(service.createUser(userDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(service.updatedUser(userDto));
    }
}
