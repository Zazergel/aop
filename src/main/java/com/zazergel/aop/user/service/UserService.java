package com.zazergel.aop.user.service;

import com.zazergel.aop.exception.NotFoundException;
import com.zazergel.aop.user.dto.UserDto;
import com.zazergel.aop.user.dto.UserExtendedDto;
import com.zazergel.aop.user.mapper.UserMapper;
import com.zazergel.aop.user.model.User;
import com.zazergel.aop.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Transactional
    public User create(UserDto userDto) {
        return userRepo.save(userMapper.toUser(userDto));
    }

    public List<UserExtendedDto> getUsers() {
        return userRepo.findAll().stream().map(userMapper::toUserExtendedDto).toList();
    }

    public UserExtendedDto getUserById(Long id) {
        return userMapper.toUserExtendedDto(findUserById(id));
    }

    @Transactional
    public UserExtendedDto update(Long id, UserDto userDto) {
        User user = findUserById(id);

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        return userMapper.toUserExtendedDto(userRepo.save(user));
    }

    @Transactional
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    private User findUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("User with id" + id + " does not exist!"));
    }

}
