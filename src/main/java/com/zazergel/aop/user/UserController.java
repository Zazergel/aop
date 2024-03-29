package com.zazergel.aop.user;

import com.zazergel.aop.user.dto.UserDto;
import com.zazergel.aop.user.dto.UserExtendedDto;
import com.zazergel.aop.user.model.User;
import com.zazergel.aop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @GetMapping("/{id}")
    public UserExtendedDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserExtendedDto> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/{id}")
    public UserExtendedDto update(@PathVariable Long id,
                          @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
