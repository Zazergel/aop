package com.zazergel.aop.order.mapper;

import com.zazergel.aop.order.dto.OrderDto;
import com.zazergel.aop.order.model.Order;
import com.zazergel.aop.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "userId", expression = "java(order.getUser().getId())")
    OrderDto toOrderDto(Order order);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "description", expression = "java(orderDto.getDescription())")
    @Mapping(target = "status", expression = "java(\"NEW\")")
    @Mapping(target = "user", expression = "java(user)")
    Order toOrder(OrderDto orderDto, User user);
}
