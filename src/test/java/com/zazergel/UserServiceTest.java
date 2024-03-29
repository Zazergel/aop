package com.zazergel;

import com.zazergel.aop.AopApplication;
import com.zazergel.aop.aspect.LoggingAspect;
import com.zazergel.aop.user.dto.UserDto;
import com.zazergel.aop.user.dto.UserExtendedDto;
import com.zazergel.aop.user.mapper.UserMapper;
import com.zazergel.aop.user.model.User;
import com.zazergel.aop.user.repository.UserRepo;
import com.zazergel.aop.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = AopApplication.class)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@Import(LoggingAspect.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private UserMapper userMapper;

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto("Test User", "test@example.com");
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        when(userMapper.toUser(any(UserDto.class))).thenReturn(user);
        when(userRepo.save(any(User.class))).thenReturn(user);

        userService.create(userDto);

        verify(userMapper).toUser(any(UserDto.class));
        verify(userRepo).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        UserExtendedDto userExtendedDto = new UserExtendedDto();

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toUserExtendedDto(any(User.class))).thenReturn(userExtendedDto);

        userService.getUserById(userId);

        verify(userRepo).findById(userId);
        verify(userMapper).toUserExtendedDto(any(User.class));
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        UserDto userDto = new UserDto("Updated User", "updated@example.com");
        User user = new User();
        user.setId(userId);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        UserExtendedDto userExtendedDto = new UserExtendedDto();

        when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        when(userRepo.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserExtendedDto(any(User.class))).thenReturn(userExtendedDto);

        userService.update(userId, userDto);

        verify(userRepo).save(any(User.class));
        verify(userMapper).toUserExtendedDto(any(User.class));
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepo).deleteById(userId);
        userService.delete(userId);
        verify(userRepo).deleteById(userId);
    }
}


