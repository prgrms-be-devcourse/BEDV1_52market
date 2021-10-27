package prgrms.al.back.user.controller;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prgrms.al.back.user.dto.UserDto;
import prgrms.al.back.user.service.UserServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Users")
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
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.createUser(userDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(service.updatedUser(userDto));
    }
}
