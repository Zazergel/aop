package com.zazergel.aop.user.mapper;

import com.zazergel.aop.user.dto.UserDto;
import com.zazergel.aop.user.dto.UserExtendedDto;
import com.zazergel.aop.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "name", expression = "java(userDto.getName())")
    @Mapping(target = "email", expression = "java(userDto.getEmail())")
    User toUser(UserDto userDto);

    @Mapping(target = "id", expression = "java(user.getId())")
    @Mapping(target = "name", expression = "java(user.getName())")
    @Mapping(target = "email", expression = "java(user.getEmail())")
    @Mapping(target = "countOfOrders", expression = "java(user.getOrders().size())")
    UserExtendedDto toUserExtendedDto(User user);
}